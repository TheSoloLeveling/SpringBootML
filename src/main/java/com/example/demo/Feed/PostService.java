package com.example.demo.Feed;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ArrayList<Post> submitPostToDB(Post body) {
        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        body.setPostID(UUID.randomUUID());
        body.setLikes(0);
        body.setDateTime(dateTime);


        postRepository.save(body);
        ArrayList<Post> result = retrivePostFromDB();
        return result;
    }

    public ArrayList<Post> retrivePostFromDB() {
        ArrayList<Post> result= (ArrayList<Post>) postRepository.findAll();
        return result;
    }

    public ArrayList<Post> deletePostFromDB(UUID postID) {
        postRepository.deleteById(postID);

        ArrayList<Post> result=retrivePostFromDB();
        return result;
    }

    public ResponseEntity<Post> findPostById(UUID postID) {
        Post post = postRepository.findById(postID)
                .orElseThrow( () -> new ResourceNotFoundException("Post with id " + postID + " not found"));
        System.out.println(postID);
        return ResponseEntity.ok().body(post);

    }
}
