package com.example.demo.services;

import com.example.demo.bucket.BucketName;
import com.example.demo.entities.Club;
import com.example.demo.entities.UserBD;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
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

    public UserBDService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    public List<UserBD> getUsers() {
        return userBDRepository.findAll();
    }

    public byte[] downloadImageCover(Long idUser) {
        UserBD userBD = userBDRepository.getById(idUser) ;
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), userBD.getId());
        return userBD.getCover()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public byte[] downloadImageIcon(Long idUser) {
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
        UserBD userBD = getUsers().stream().filter(userfilter -> userfilter.getIcon().equals(idUser))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Club " + idUser + "doesn't exist"));

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
        UserBD userBD = getUsers().stream().filter(userfilter -> userfilter.getIcon().equals(idUser))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Club " + idUser + "doesn't exist"));

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
                .orElseThrow( () -> new ResourceNotFoundException("Club with id " + idUser + " not found"));

        user.setIcon(c.getIcon().get());
        user.setUserName(c.getUserName());
        user.setPassword(c.getPassword());
        user.setAccountNonExpired(c.isAccountNonExpired());
        user.setEnabled(c.isEnabled());
        user.setAccountNonLocked(c.isAccountNonLocked());
        user.setCredentialsNonExpired(c.isCredentialsNonExpired());
        user.setRoles(c.getRoles());

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
}
