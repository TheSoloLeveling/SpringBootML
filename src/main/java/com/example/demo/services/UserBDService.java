package com.example.demo.services;

import com.example.demo.bucket.BucketName;
import com.example.demo.entities.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.MembreRepository;
import com.example.demo.repositories.UserBDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserBDService {
    private final FileStore fileStore;

    @Autowired
    UserBDRepository userBDRepository;

    @Autowired
    ClubRepository clubRepository  ;

    @Autowired
    MembreRepository membreRepository;

    @Autowired
    ClubService clubService;



    public UserBDService(FileStore fileStore) {
        this.fileStore = fileStore;
    }


    public void followClub(String nomClub, Long idUser) {
        Club club = clubService.getClubBynomClub(nomClub).getBody();
        if (club.getNbrFollowers() == null) {
            club.setNbrFollowers(1);
        }
        else
            club.setNbrFollowers(club.getNbrFollowers() + 1);

        UserBD userBD = getUserById(idUser).getBody();
        Set<ClubFollowed> clubFollowed = userBD.getFollowedTo();

        clubFollowed.add(new ClubFollowed(nomClub, club.getIdClub()));

        userBD.setFollowedTo(clubFollowed);
        System.out.println("follow : " + userBD.getFollowedTo());
        clubRepository.save(club);
        userBDRepository.save(userBD);
    }

    public void unfollowClub(String nomClub, Long idUser) {
        Club club = clubService.getClubBynomClub(nomClub).getBody();

        club.setNbrFollowers(club.getNbrFollowers() - 1);

        UserBD userBD = getUserById(idUser).getBody();
        Set<ClubFollowed> clubFollowed = userBD.getFollowedTo();
        clubFollowed.remove(findClubFollowed(clubFollowed, club.getIdClub()));

        userBD.setFollowedTo(clubFollowed);
        System.out.println("unfollow : " + clubFollowed);
        clubRepository.save(club);
        userBDRepository.save(userBD);
    }

    public ClubFollowed findClubFollowed(Set<ClubFollowed> clubFollowed, Integer idClub) {
        for(ClubFollowed s : clubFollowed){
            System.out.println(s.getIdClubFollowed().longValue());
            if (s.getIdClub().equals(idClub))
                return  s;
        }
        return null;
    }

    public List<Club> findClubYouOwn(String nameUserPresident) {

        List<Club> op = new ArrayList<>();
        Membre president = null;
        int check = 0;
        List<Membre> m = membreRepository.findAllByNameUser(nameUserPresident);
        for (int i = 0; i < m.size(); i++) {
            Set<Fonctionnalite> s = m.get(i).getFonctionnalites();
            for(Fonctionnalite fonctionnalite : s)
                if (fonctionnalite.getName().equals(EFonction.PRESIDENT)){
                    president = m.get(i);
                    check = 1;
                    break;
                }
        }
        if (check == 1){
            op.add(clubRepository.findByIdClub(president.getIdClub()).get());
            return op;
        }
        else
            return null;

    }

    public List<Club> findAllClubsFollowed(Long id) {

        List<Club> op = new ArrayList<>();
        UserBD userBD = getUserById(id).getBody();
        Set<ClubFollowed> s = userBD.getFollowedTo();
        for(ClubFollowed clubFollowed : s)
            op.add(clubService.getClubById(clubFollowed.getIdClub()).getBody());

        return op;
    }

    public List<UserBD> getUsers() {
        return userBDRepository.findAll();
    }

    public byte[] downloadImageCover(Long idUser) {
        if(idUser == 0)
            return null;
        UserBD userBD = userBDRepository.getById(idUser) ;
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), userBD.getId());
        return userBD.getCover()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public byte[] downloadImageIcon(Long idUser) {
        if(idUser == 0)
            return null;
        UserBD userBD = userBDRepository.getById(idUser) ;
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), userBD.getId());
        return userBD.getIcon()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public void uploadImageIcon(Long idUser, MultipartFile file) throws IOException {
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
        UserBD userBD = getUserById(idUser).getBody();

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), userBD.getId());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            userBD.setIcon(fileName);
            updateUserBD(idUser, userBD);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void uploadImageCover(Long idUser, MultipartFile file) throws IOException {
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
        UserBD userBD = getUserById(idUser).getBody();

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), userBD.getId());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            userBD.setCover(fileName);
            updateUserBD(idUser, userBD);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserBD> updateUserBD(@PathVariable("id") Long idUser,
                                           @Validated @RequestBody UserBD c) {

        UserBD user = userBDRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id : " + idUser + " not found"));


        user.setUserName(c.getUserName());
        user.setPassword(c.getPassword());
        user.setEmail(c.getEmail());

        return ResponseEntity.ok().body(userBDRepository.save(user));
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserBD> getUserByusername(@PathVariable String username) throws ResourceNotFoundException {
        System.out.println(username);
        UserBD club = userBDRepository.findByuserName(username)
                .orElseThrow( () -> new ResourceNotFoundException("Club with username " + username + " not found"));

        System.out.println(userBDRepository.findByuserName(username));
        return ResponseEntity.ok().body(club);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserBD> getUserById(@PathVariable("id") Long idUser){

        UserBD userBD = userBDRepository.findById(idUser)
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idUser + " not found"));

        return ResponseEntity.ok().body(userBD);
    }
}
