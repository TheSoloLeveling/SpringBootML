package com.example.demo.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
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

    public Optional<Post> findPostById(UUID postID) {
        postRepository.deleteById(postID);

        return postRepository.findById(postID);

    }
}
