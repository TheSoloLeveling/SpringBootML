package com.example.demo.services;


import com.example.demo.Feed.Post;
import com.example.demo.Feed.PostRepository;
import com.example.demo.entities.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
import com.example.demo.bucket.BucketName;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.FonctionnaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class ClubService {

    private final FileStore fileStore;
    public ClubService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    FonctionnaliteRepository fonctionnaliteRepository;

    @Autowired
    private UserBDService userBDService;

    @Autowired
    PostRepository postRepository;

    public void uploadImageLogo(Integer idClub, MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_WEBP.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("file must be an image");
        }

        //create metadata for the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //Check the club exist
        Club club = getClubById(idClub).getBody();

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher logo est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            club.setLogo(fileName);
            clubRepository.save(club);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void uploadImageCover(Integer idClub, MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }

        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_WEBP.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("file must be an image");
        }

        //create metadata for the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //Check the club exist
        Club club = getClubById(idClub).getBody();

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher cover est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            club.setCoverImg(fileName);
            clubRepository.save(club);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public byte[] downloadImageLogo(Integer idClub) {
        if(idClub == 0)
            return null;
        Club club = getClubById(idClub).getBody();
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        return club.getLogo()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    public byte[] downloadImageCover(Integer idClub) {
        if(idClub == 0)
            return null;
        Club club = getClubById(idClub).getBody();
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        return club.getCoverImg()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    public Club getClubByPost(Integer idPost) {
        Post post = postRepository.getById(idPost);

        return clubRepository.getById(post.getIdClub());
    }

    public List<Club> findClubsWithSorting(String field, boolean order){
        if (order)
            return clubRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        else
            return clubRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }



    public Club createClub(Club c,Referent referent, Membre president, Membre vicePresident, Membre tresorier, Membre secretaire) throws IOException {
        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);
        Club club = new Club();
        club.setNomClub(c.getNomClub());
        club.setDescClub(c.getDescClub());
        club.setStatus(false);
        club.setDateCre(dateTime);
        Set<Membre> m = new HashSet<Membre>();

        Long idP = userBDService.getUserByusername(president.getNameUser()).getBody().getId();
        president.setIdUser(idP);

        Long idV = userBDService.getUserByusername(vicePresident.getNameUser()).getBody().getId();
        vicePresident.setIdUser(idV);

        Long idS = userBDService.getUserByusername(secretaire.getNameUser()).getBody().getId();
        secretaire.setIdUser(idS);

        Long idT = userBDService.getUserByusername(tresorier.getNameUser()).getBody().getId();
        tresorier.setIdUser(idT);

        Long idR = userBDService.getUserByusername(tresorier.getNameUser()).getBody().getId();
        referent.setIdUser(idR);

        Set<Fonctionnalite> fonctionsP = new HashSet<>();
        Set<Fonctionnalite> fonctionsV = new HashSet<>();
        Set<Fonctionnalite> fonctionsS = new HashSet<>();
        Set<Fonctionnalite> fonctionsT = new HashSet<>();

        Fonctionnalite fonctionnaliteP = fonctionnaliteRepository.findByName(EFonction.PRESIDENT)
                .orElseThrow(() -> new RuntimeException("Error: Function is not found."));
        fonctionsP.add(fonctionnaliteP);
        president.setFonctionnalites(fonctionsP);

        Fonctionnalite fonctionnaliteV = fonctionnaliteRepository.findByName(EFonction.VICEPRESIDENT)
                .orElseThrow(() -> new RuntimeException("Error: Function is not found."));
        fonctionsV.add(fonctionnaliteV);
        vicePresident.setFonctionnalites(fonctionsV);

        Fonctionnalite fonctionnaliteS = fonctionnaliteRepository.findByName(EFonction.SECRETARY)
                .orElseThrow(() -> new RuntimeException("Error: Function is not found."));
        fonctionsS.add(fonctionnaliteS);
        secretaire.setFonctionnalites(fonctionsS);

        Fonctionnalite fonctionnaliteT = fonctionnaliteRepository.findByName(EFonction.TREASURER)
                .orElseThrow(() -> new RuntimeException("Error: Function is not found."));
        fonctionsT.add(fonctionnaliteT);
        tresorier.setFonctionnalites(fonctionsT);

        president.setDateCre(dateTime);
        vicePresident.setDateCre(dateTime);
        secretaire.setDateCre(dateTime);
        tresorier.setDateCre(dateTime);

        m.add(president);
        m.add(vicePresident);
        m.add(tresorier);
        m.add(secretaire);
        club.setReferent(referent);
        club.setMembres(m);

        return clubRepository.save(club);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Club> getClubById(@PathVariable("id") Integer idClub){

        Club club = clubRepository.findByIdClub(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));

        return ResponseEntity.ok().body(club);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Club> getClubBynomClub(@RequestParam(required=false) String nomClub) throws ResourceNotFoundException {

        Club club = clubRepository.findByNomClub(nomClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with name  " + nomClub + " not found"));
        System.out.println(club.getMembres().toString());
        return ResponseEntity.ok().body(club);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Club> updateClub(@PathVariable("id") Integer idClub,
                                           @Validated @RequestBody Club c) {

        Club club = clubRepository.findByIdClub(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));

        club.setLogo(c.getLogo().orElse(null));
        club.setNomClub(c.getNomClub());
        club.setDescClub(c.getDescClub());
        club.setDateCre(c.getDateCre());
        club.setCoverImg(c.getCoverImg().orElse(null));
        club.setMembres(c.getMembres());
        club.setReferent(c.getReferent());
        club.setStatus(c.isStatus());

        return ResponseEntity.ok().body(clubRepository.save(club));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteClub(Integer idClub) {
        Club club = clubRepository.findByIdClub(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));
        clubRepository.delete(club);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
