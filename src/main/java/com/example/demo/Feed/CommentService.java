package com.example.demo.Feed;

import com.example.demo.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MembreService membreService;

    public Comment saveComment(Comment comment) {
        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);


        comment.setTimestamp(dateTime);
        return commentRepository.save(comment);
    }

    public ArrayList<Comment> getAllComment(Integer postID){
        ArrayList<Comment> result=new ArrayList<Comment>();
        result = commentRepository.findAllByIdPost(postID);
        return result;
    }
}
