package com.example.demo.Feed;

import com.example.demo.bucket.BucketName;
import com.example.demo.entities.*;
import com.example.demo.filestore.FileStore;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.MembreRepository;
import com.example.demo.repositories.UserBDRepository;
import com.example.demo.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    UserBDRepository userBDRepository;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    MembreRepository membreRepository;

    @Autowired
    ClubService clubService;

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

    public List<ClubEvent> getEvents() {
        return eventRepository.findAll();
    }

    public List<ClubEvent> findEventsWithSorting(String field, boolean order){
        if (order)
            return eventRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        else
            return eventRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    public List<ClubEvent> findAllEventsOfClubsFollowed(Long id, String field, boolean order) {

        List<ClubEvent> c = getEvents();
        List<Club> op = new ArrayList<>();
        List<ClubEvent> followedEvents = new ArrayList<>();

        UserBD userBD = userBDRepository.getById(id);
        Set<ClubFollowed> s = userBD.getFollowedTo();
        for(ClubFollowed clubFollowed : s)
            op.add(clubRepository.getById(clubFollowed.getIdClub()));

        for (ClubEvent event : c) {
            Post post = postRepository.getById(event.getIdPost());
            Club club = clubRepository.getById(post.getIdClub());
            if (op.contains(club))
                followedEvents.add(event);
        }

        if (field.equals("name") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getName));
        if (field.equals("name") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getName).reversed());
        if (field.equals("startDate") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getStartDate));
        if (field.equals("startDate") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getStartDate).reversed());
        if (field.equals("actualP") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getActualP));
        if (field.equals("actualP") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getActualP).reversed());

        return followedEvents;
    }

    public List<ClubEvent> findAllEventsOfClubsJoined(Long id, String field, boolean order) {

        List<ClubEvent> c = getEvents();
        List<Club> op = new ArrayList<>();
        List<ClubEvent> followedEvents = new ArrayList<>();

        List<Membre> m = membreRepository.findAllByIdUser(id);
        System.out.println(m);
        for (Membre membre : m) {
            op.add(clubService.getClubById(membre.getIdClub()).getBody());
        }

        for (ClubEvent event : c) {
            Post post = postRepository.getById(event.getIdPost());
            Club club = clubRepository.getById(post.getIdClub());
            if (op.contains(club))
                followedEvents.add(event);
        }

        if (field.equals("name") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getName));
        if (field.equals("name") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getName).reversed());
        if (field.equals("startDate") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getStartDate));
        if (field.equals("startDate") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getStartDate).reversed());
        if (field.equals("actualP") && order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getActualP));
        if (field.equals("actualP") && !order)
            followedEvents.sort(Comparator.comparing(ClubEvent::getActualP).reversed());

        return followedEvents;
    }

    public List<ClubEvent> findEventOfClubYouOwn(String nameUserPresident) {

        List<ClubEvent> c = getEvents();
        List<ClubEvent> followedEvents = new ArrayList<>();
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
        }

        for (ClubEvent event : c) {
            Post post = postRepository.getById(event.getIdPost());
            Club club = clubRepository.getById(post.getIdClub());
            if (op.contains(club))
                followedEvents.add(event);
        }

        return followedEvents;

    }
}
