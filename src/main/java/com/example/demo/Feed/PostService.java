package com.example.demo.Feed;

import com.example.demo.bucket.BucketName;
import com.example.demo.entities.Club;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.filestore.FileStore;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.services.ClubService;
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

    public Post submitPostToDB(String desc, String nameClub, Long idUser, String userName) {

        Post body = new Post();
        body.setDescription(desc);
        body.setUserID(idUser);
        body.setUserName(userName);
        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);
        body.setComments(new HashSet<Comment>());
        body.setLikes(0);
        body.setDateTime(dateTime);

        Club c = clubService.getClubBynomClub(nameClub).getBody();
        Set<Post> p = c.getPosts();
        p.add(body);
        c.setPosts(p);
        clubRepository.save(c);
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

        return p;
    }

    public List<Post> retrievePostsClub(String nameClub) {

        Club c = clubService.getClubBynomClub(nameClub).getBody();

        if (!c.getPosts().isEmpty()) {
            return new LinkedList<>(c.getPosts());
        }

        return null;
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
