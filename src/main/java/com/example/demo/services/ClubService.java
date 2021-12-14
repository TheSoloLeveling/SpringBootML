package com.example.demo.services;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
import com.example.demo.bucket.BucketName;
import com.example.demo.entities.Club;
import com.example.demo.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class ClubService {

    private final FileStore fileStore;

    @Autowired
    ClubRepository clubRepository;

    public ClubService(FileStore fileStore) {
        this.fileStore = fileStore;
    }


    public void uploadImage(String idClub, MultipartFile file) throws IOException {
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
        Club club = getClubs().stream().filter(clubfilter -> clubfilter.getIdClub().equals(idClub))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Club " + idClub + "doesn't exist"));

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            club.setLogo(fileName);
            updateClub(idClub, club);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }


    public byte[] downloadImage(String idClub) {
        Club club = getClubById(idClub).getBody();
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), club.getIdClub());
        return club.getLogo()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }


    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Club createClub(@RequestBody Club club){
        return clubRepository.save(club);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Club> getClubById(@PathVariable("id") String idClub){

        Club club = clubRepository.findById(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));

        return ResponseEntity.ok().body(club);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<Club> getClubBynomClub(@RequestParam String nomClub) throws ResourceNotFoundException {
        Club club = clubRepository.findByNomClub(nomClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + nomClub + " not found"));

        return ResponseEntity.ok().body(club);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Club> updateClub(@PathVariable("id") String idClub,
                                           @Validated @RequestBody Club c) {

        Club club = clubRepository.findById(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));

        club.setLogo(c.getLogo().get());
        club.setNomClub(c.getNomClub());
        club.setDescClub(c.getDescClub());
        club.setAffiliation(c.getAffiliation());
        club.setActivites(c.getActivites());
        club.setDateCre(c.getDateCre());
        club.setCategorie(c.getCategorie());
        club.setCoverImg(c.getCoverImg());
        club.setMembres(c.getMembres());
        club.setPostes(c.getPostes());
        club.setReferent(c.getReferent());
        club.setTresorerie(c.getTresorerie());
        club.setStatus(c.isStatus());
        club.setReunions(c.getReunions());

        return ResponseEntity.ok().body(clubRepository.save(club));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteClub(String idClub) {
        Club club = clubRepository.findById(idClub)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idClub + " not found"));
        clubRepository.delete(club);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
