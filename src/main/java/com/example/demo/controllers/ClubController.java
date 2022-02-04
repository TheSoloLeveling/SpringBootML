package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.exception.ResourceNotFoundException;
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
@RequestMapping(path = "/api/clubService/")
@CrossOrigin("*")
public class ClubController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubController.class);

    @Autowired
    ClubService clubService;


    @CrossOrigin("*")
    @GetMapping(path = "/getClubs")
    public List<Club> getClubs(){
        return clubService.getClubs();
    }

    @CrossOrigin("*")
    @GetMapping(path = "/getClubs/{field}/{order}")
    public List<Club> getClubsWithSort(@PathVariable String field, @PathVariable boolean order){
        return clubService.findClubsWithSorting(field, order);
    }

    @GetMapping("/findClubByPost/{idPost}")
    public Club findClubByPost(@PathVariable Integer idPost) {
        return clubService.getClubByPost(idPost);
    }

    @CrossOrigin("*")
    @PostMapping(value = "/save", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Club createClub(@RequestBody RequestCreateClub r) throws IOException {
        System.out.println(r);
        System.out.println(r.toString());
        return clubService.createClub(r.getClubRequest(), r.getReferentRequest(), r.getPresidentRequest(), r.getVicePresidentRequest(), r.getTresorierRequest(), r.getSecretaireRequest()); //clubService.createClub(club)
    }

    @CrossOrigin("*")
    @PutMapping(value = "/update", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Club updateClub(@RequestBody RequestUpdateClub r) throws IOException {
        System.out.println(r);
        System.out.println(r.toString());
        return clubService.updateClub(r.getClubRequest(), r.getReferentRequest(), r.getPresidentRequest(), r.getVicePresidentRequest(), r.getTresorierRequest(), r.getSecretaireRequest(), r.getNameClub()); //clubService.createClub(club)
    }

    @CrossOrigin("*")
    @GetMapping("/findClubById/{id}")
    public ResponseEntity<Club> findClubById(@PathVariable("id") Integer idClub) {
        return clubService.getClubById(idClub);
    }

    @CrossOrigin("*")
    @GetMapping("/getClubName/{id}")
    public String getClubName(@PathVariable("id") Integer idClub) {
        Club c = clubService.getClubById(idClub).getBody();
        return c.getNomClub();
    }

    @CrossOrigin("*")
    @GetMapping("/findClubByName/{nameClub}")
    public Club findClubByNomClub(@PathVariable("nameClub") String nomClub) {
        return clubService.getClubBynomClub(nomClub).getBody();
    }

    @DeleteMapping("deleteClub/{id}")
    public Map<String, Boolean> deleteClub(@PathVariable("id") Integer idClub) throws ResourceNotFoundException {
        return clubService.deleteClub(idClub);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE

    @CrossOrigin("*")
    @PostMapping(
            path = "{idUser}/image/uploadIcon",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageIcon(@PathVariable("idUser") Integer idClub,
                                @RequestParam("file") MultipartFile file) throws IOException {
        clubService.uploadImageLogo(idClub, file);
    }

    @CrossOrigin("*")
    @PostMapping(
            path = "{idUser}/image/uploadCover",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImageCover(@PathVariable("idUser") Integer idClub,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        clubService.uploadImageCover(idClub, file);
    }

    @CrossOrigin("*")
    @GetMapping(path = "/landing/{idUser}/image/downloadCover")
    public byte[] downloadImageCover(@PathVariable("idUser") Integer idClub) {
        return  clubService.downloadImageCover(idClub);
    }

    @CrossOrigin("*")
    @GetMapping(path = "/landing/{idUser}/image/downloadIcon")
    public byte[] downloadImageLogo(@PathVariable("idUser") Integer idClub) {
        return  clubService.downloadImageLogo(idClub);
    }
    //DO NOT ENABLE              DO NOT ENABLE            DO NOT ENABLE                    DO NOT ENABLE          DO NOT ENABLE
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
