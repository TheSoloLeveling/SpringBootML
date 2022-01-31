package com.example.demo.Feed;

import com.example.demo.entities.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/eventService")
@CrossOrigin("*")
public class EventController {

    @Autowired
    EventService eventService;

    @CrossOrigin("*")
    @GetMapping(path = "/landing/{idEvent}/file/downloadEventFile")
    public byte[] downloadImagePost(@PathVariable("idEvent") Integer idEvent) {
        return  eventService.downloadFile(idEvent);
    }

    @PostMapping("/save")
    public ClubEvent submitPost(@RequestBody EventRequest body){
        return eventService.submitEventToDB(body.getName(), body.getEventDesc(), body.getStartDate(),
                body.getEndDate(), body.getMaxP(), body.getBudgetE(), body.getBuilding(),
                body.getAmphi(), body.getActuaP(), body.getPostID());
    }

    @CrossOrigin("*")
    @PostMapping(
            path = "{idEvent}/file/uploadFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadVideoPost(@PathVariable("idEvent") Integer idEvent,
                                @RequestParam("file") MultipartFile file) throws IOException {
        eventService.uploadFile(idEvent, file);
    }

    @GetMapping("/getLastEvent")
    public ClubEvent retrieveLastPost(){
        return eventService.getLastEvent();
    }
}
