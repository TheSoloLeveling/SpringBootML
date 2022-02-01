package com.example.demo.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/commentService")
public class CommentController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;


    @GetMapping("/getAllComments/{postID}")
    public ArrayList<Comment> getAllComments(@PathVariable("postID") Integer postID){
        return commentService.getAllComment(postID);

    }
}
