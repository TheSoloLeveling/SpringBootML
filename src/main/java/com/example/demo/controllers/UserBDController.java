package com.example.demo.controllers;

import com.example.demo.entities.Club;
import com.example.demo.entities.UserBD;
import com.example.demo.exception.ResourceNotFoundException;
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

    @GetMapping("/findUser/{username}")
    public ResponseEntity<UserBD> findUserByusername(@PathVariable String username) {
        return userBDService.getUserByusername(username);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    @PostMapping(
            path = "{idUser}/image/uploadIcon",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageIcon(@PathVariable("idUser") Long idUser,
                            @RequestParam("file") MultipartFile file) throws IOException {
        userBDService.uploadImageIcon(idUser, file);
    }

    @PostMapping(
            path = "{idUser}/image/uploadCover",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageCover(@PathVariable("idUser") Long idUser,
                            @RequestParam("file") MultipartFile file) throws IOException {
        userBDService.uploadImageCover(idUser, file);
    }

    @GetMapping(path = "{idUser}/image/downloadCover")
    public byte[] downloadImageCover(@PathVariable("idUser") Long idUser) {
        return userBDService.downloadImageCover(idUser);
    }
    @GetMapping(path = "{idUser}/image/downloadIcon")
    public byte[] downloadImageLogo(@PathVariable("idUser") Long idUser) {
        return userBDService.downloadImageIcon(idUser);
    }
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
