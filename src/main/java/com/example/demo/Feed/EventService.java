package com.example.demo.Feed;

import com.example.demo.bucket.BucketName;
import com.example.demo.entities.Club;
import com.example.demo.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class EventService {

    private final FileStore fileStore;
    public EventService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Autowired
    EventRepository eventRepository;

    @Autowired
    PostRepository postRepository;

    public ClubEvent submitEventToDB(String name, String eventDesc, Timestamp startDate, Timestamp endDate,
                                int maxP, float budgetE, int building, int amphi, int actuaP, Integer postID) {

        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setName(name);
        clubEvent.setEventDesc(eventDesc);
        clubEvent.setAmphi(amphi);
        clubEvent.setActualP(actuaP);
        clubEvent.setBudgetE(budgetE);
        clubEvent.setEndDate(endDate);
        clubEvent.setMaxP(maxP);
        clubEvent.setStartDate(startDate);
        clubEvent.setBuilding(building);

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        clubEvent.setDateTime(dateTime);
        clubEvent.setIdPost(postID);
        return  eventRepository.save(clubEvent);

    }

    public void uploadFile(Integer idEvent, MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }

        //create metadata for the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //Check the club exist
        ClubEvent clubEvent = eventRepository.getById(idEvent);
        Post post = postRepository.getById(clubEvent.getIdPost());

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher logo est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            clubEvent.setMateriels(fileName);
            eventRepository.save(clubEvent);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadFile(Integer eventID) {
        if(eventID == 0)
            return null;
        ClubEvent clubEvent = eventRepository.getById(eventID);
        Post post = postRepository.getById(clubEvent.getIdPost());
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        return clubEvent.getMateriels()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    public ClubEvent getLastEvent() {
        return eventRepository.findTopByOrderByDateTimeDesc();
    }
}
