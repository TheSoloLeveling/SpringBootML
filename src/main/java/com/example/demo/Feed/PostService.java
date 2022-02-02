package com.example.demo.Feed;

import com.example.demo.bucket.BucketName;
import com.example.demo.config.ServletCustomizer;
import com.example.demo.entities.Club;
import com.example.demo.entities.UserBD;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.MembreRepository;
import com.example.demo.repositories.UserBDRepository;
import com.example.demo.services.ClubService;
import com.example.demo.services.MembreService;
import com.example.demo.services.UserBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static org.apache.http.entity.ContentType.*;
import static org.apache.http.entity.ContentType.IMAGE_WEBP;



@Service
public class PostService {

    private final FileStore fileStore;
    public PostService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Autowired
    PostRepository postRepository;

    @Autowired
    ClubService clubService;

    @Autowired
    UserBDService userBDService;

    @Autowired
    ClubRepository clubRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserBDRepository userBDRepository;

    @Autowired
    MembreService membreService;

    @Autowired
    EventRepository eventRepository;

    public void uploadImagePost(Integer idPost, MultipartFile file) throws IOException {
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
        Post post = postRepository.getById(idPost);

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher logo est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            post.setPostImgURL(fileName);
            postRepository.save(post);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void uploadVideoPost(Integer idPost, MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new IllegalStateException("Cannot upload empty file");
        }

        //create metadata for the file
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        //Check the club exist
        Post post = postRepository.getById(idPost);

        //save the file in the ressource folder
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        String fileName = String.format("%s/%s", file.getName(), UUID.randomUUID());
        try {
            System.out.println("Nom du ficher logo est : " + fileName);
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());
            post.setPostVideo(fileName);
            postRepository.save(post);

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadImage(Integer postID) {
        if(postID == 0)
            return null;
        Post post = findPostById(postID).getBody();
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        return post.getPostImgURL()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    public byte[] downloadVideo(Integer postID) {
        if(postID == 0)
            return null;
        Post post = findPostById(postID).getBody();
        String path = String.format("%s/%s", BucketName.CLUB_IMAGE.getBucketName(), post.getIdClub());
        return post.getPostVideo()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);

    }

    public Post getLastPost() {
        return postRepository.findTopByOrderByDateTimeDesc();
    }

    public Post submitPostToDB(String desc, String nameClub, Long idUser, String userName) {

        Post body = new Post();
        body.setClubName(nameClub);
        body.setDescription(desc);
        body.setUserID(idUser);
        body.setUserName(userName);
        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);
        body.setComments(new HashSet<Comment>());
        body.setLikes(0);
        body.setDateTime(dateTime);
        body.setEvent(false);

        Club c = clubService.getClubBynomClub(nameClub).getBody();
        Set<Post> p = c.getPosts();
        p.add(body);
        c.setPosts(p);
        clubRepository.saveAndFlush(c);
        return body;
    }

    public int getCommentsSize(Integer postID) {
        Post p = postRepository.getById(postID);
        return p.getComments().size();
    }

    public void likePost(Integer postID){
        Post p = postRepository.getById(postID);
        p.setLikes(p.getLikes() + 1);
        postRepository.save(p);
    }

    public List<Post> retrivePostFromDB() {
         return postRepository.findAll();

    }

    public List<Post> retrievePostClubFollowed(Long id) {

        List<Post> p = new LinkedList<>();
        List<Club> c =  userBDService.findAllClubsFollowed(id, "null", false);

        for (Club club : c) {
            p.addAll(club.getPosts());
        }

        p.sort((o1, o2) -> {
            if (o1.getDateTime() == null || o2.getDateTime() == null)
                return 0;
            if(o1.isEvent() && !o2.isEvent())
                return eventRepository.findByIdPost(o1.getPostID()).getStartDate().compareTo(o2.getDateTime());
            if (o2.isEvent() && !o1.isEvent())
                return o1.getDateTime().compareTo(eventRepository.findByIdPost(o2.getPostID()).getStartDate());
            if (o1.isEvent() && o2.isEvent())
                return eventRepository.findByIdPost(o1.getPostID()).getStartDate().compareTo(eventRepository.findByIdPost(o2.getPostID()).getStartDate());
            return o1.getDateTime().compareTo(o2.getDateTime());
        });

        return p;
    }

    public Comment saveComment(Integer idClub, Integer idPost, Long idUser, String text){
        Comment comment = new Comment(idUser, idClub, idPost, text);

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        comment.setDateTime(dateTime);

        Club club = clubRepository.getById(idClub);
        comment.setNomClub(club.getNomClub());

        UserBD userBD = userBDRepository.getById(idUser);
        comment.setUsername(userBD.getUserName());
        comment.setUserIcon(userBD.getIcon().get());
        comment.setUserRole(membreService.findMemberRole(idUser, idClub));

        Post post = postRepository.getById(idPost);
        Set<Comment> c = post.getComments();
        c.add(comment);
        post.setComments(c);
        postRepository.save(post);
        return comment;
    }

    public List<Post> retrievePostsClub(String nameClub) {
        Set<Post> p = new HashSet<>();
        Club c = clubService.getClubBynomClub(nameClub).getBody();

        if (!c.getPosts().isEmpty()) {
            p = c.getPosts();
        }

        List<Post> l = new LinkedList<>(p);

        l.sort((o1, o2) -> {
            if (o1.getDateTime() == null || o2.getDateTime() == null)
                return 0;
            if(o1.isEvent() && !o2.isEvent())
                return eventRepository.findByIdPost(o1.getPostID()).getStartDate().compareTo(o2.getDateTime());
            if (o2.isEvent() && !o1.isEvent())
                return o1.getDateTime().compareTo(eventRepository.findByIdPost(o2.getPostID()).getStartDate());
            if (o1.isEvent() && o2.isEvent())
                return eventRepository.findByIdPost(o1.getPostID()).getStartDate().compareTo(eventRepository.findByIdPost(o2.getPostID()).getStartDate());
            return o1.getDateTime().compareTo(o2.getDateTime());
        });

        return l;
    }

        public List<Post> deletePostFromDB(Integer postID) {
        postRepository.deleteById(postID);

        List<Post> result=retrivePostFromDB();
        return result;
    }

    public ResponseEntity<Post> findPostById(Integer postID) {
        Post post = postRepository.findById(postID)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id " + postID + " not found"));
        System.out.println(postID);
        return ResponseEntity.ok().body(post);

    }
}
