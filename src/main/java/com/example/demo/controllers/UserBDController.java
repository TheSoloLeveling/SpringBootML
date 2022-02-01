package com.example.demo.controllers;

import com.example.demo.entities.Club;
import com.example.demo.entities.FollowRequest;
import com.example.demo.entities.UserBD;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repositories.UserBDRepository;
import com.example.demo.services.ClubService;
import com.example.demo.services.UserBDService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin("*")
public class UserBDController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBDController.class);

    @Autowired
    UserBDService userBDService;

    @Autowired
    UserBDRepository userBDRepository;

    @PostMapping(value = "/follow", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void followClub(@RequestBody FollowRequest f) throws IOException {
        userBDService.followClub(f.getNomClub(), f.getIdUser());
    }

    @PostMapping(value = "/unfollow", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void unfollowClub(@RequestBody FollowRequest f) throws IOException {
        userBDService.unfollowClub(f.getNomClub(), f.getIdUser());
    }

    @GetMapping("/findUser/{username}")
    public ResponseEntity<UserBD> findUserByusername(@PathVariable String username) {
        return userBDService.getUserByusername(username);
    }

    @GetMapping("/getUserById/{idUser}")
    public UserBD getUserById(@PathVariable("idUser") Long idUser) {
        return userBDRepository.getById(idUser);
    }

    @GetMapping("/findClubOwned/{nameUser}")
    public List<Club> findClubOwned(@PathVariable String nameUser) {
        return userBDService.findClubYouOwn(nameUser);
    }

    @GetMapping("/findClubFollowed/{idUser}/{field}/{order}")
    public List<Club> findClubFollowed(@PathVariable Long idUser, @PathVariable String field, @PathVariable boolean order) {
        return userBDService.findAllClubsFollowed(idUser, field, order);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    @PostMapping(
            path = "/{idUser}/image/uploadIcon",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageIcon(@PathVariable("idUser") Long idUser,
                            @RequestParam("file") MultipartFile file) throws IOException {
        userBDService.uploadImageIcon(idUser, file);
    }

    @PostMapping(
            path = "/{idUser}/image/uploadCover",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageCover(@PathVariable("idUser") Long idUser,
                            @RequestParam("file") MultipartFile file) throws IOException {
        userBDService.uploadImageCover(idUser, file);
    }

    @GetMapping(path = "/landing/{idUser}/image/downloadCover")
    public byte[] downloadImageCover(@PathVariable("idUser") Long idUser) {
        return userBDService.downloadImageCover(idUser);
    }
    @GetMapping(path = "/landing/{idUser}/image/downloadIcon")
    public byte[] downloadImageLogo(@PathVariable("idUser") Long idUser) {
        return userBDService.downloadImageIcon(idUser);
    }
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @CrossOrigin("*")
    @GetMapping("/findUserById/{id}")
    public ResponseEntity<UserBD> findUserById(@PathVariable("id") Long idUser) {
        return userBDService.getUserById(idUser);
    }

    @CrossOrigin("*")
    @GetMapping("/checkClubFollowed/{idUser}/{nomClub}")
    public boolean checkClubFollowed(@PathVariable("idUser") Long idUser, @PathVariable("nomClub") String nomClub) {
        return userBDService.checkClubFollowed(idUser, nomClub);
    }

    @CrossOrigin("*")
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserBD> updateClub(@PathVariable("id") Long idUser,
                                           @Validated @RequestBody UserBD user)
            throws ResourceNotFoundException {
        return userBDService.updateUserBD(idUser, user);
    }
}
