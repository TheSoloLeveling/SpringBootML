package com.example.demo.controllers;

import com.example.demo.entities.Affiliation;
import com.example.demo.entities.Club;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.services.AffiliationService;
import com.example.demo.services.ClubService;
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
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class ClubController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    ClubService clubService;

    @GetMapping(path = "/club")
    public List<Club> getClubs(){
        return clubService.getClubs();
    }

    @PostMapping("/createClub")
    public Club createClub() {
        Club test = new Club("1", "UIR17", "INFORMER",
                null, true, "UIR", "UIRimage", null); //for testing
        return clubService.createClub(test); //clubService.createClub(club)
    }


    @GetMapping("/findClub/{id}")
    public ResponseEntity<Club> findClubById(@PathVariable("id") String idClub) {
        return clubService.getClubById(idClub);
    }


    @GetMapping("/findClub/{name}")
    public ResponseEntity<Club> findClubBynomClub(@RequestParam String nomClub) {
        return clubService.getClubBynomClub(nomClub);
    }

    @PutMapping("/updateClub/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable("id") String idClub,
                                           @Validated @RequestBody Club c)
            throws ResourceNotFoundException {
        return clubService.updateClub(idClub, c);
    }

    @DeleteMapping("deleteClub/{id}")
    public Map<String, Boolean> deleteClub(@PathVariable("id") String idClub) throws ResourceNotFoundException {
        return clubService.deleteClub(idClub);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    @PostMapping(
            path = "{idClub}/image/upload",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImage(@PathVariable("idClub") String idClub,
                            @RequestParam("file") MultipartFile file) throws IOException {
        clubService.uploadImage(idClub, file);
    }

    @GetMapping(path = "{idClub}/image/download")
    public byte[] downloadImage(@PathVariable("idClub") String idClub) {
        return clubService.downloadImage(idClub);
    }
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
