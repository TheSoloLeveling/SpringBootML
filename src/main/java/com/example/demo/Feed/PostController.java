package com.example.demo.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/postService")
@CrossOrigin("*")
public class PostController {

    @Autowired
    PostService postService;

    @CrossOrigin("*")
    @PostMapping(
            path = "{idUser}/image/uploadIcon",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImagePost(@PathVariable("idUser") Integer idPost,
                                @RequestParam("file") MultipartFile file) throws IOException {
        postService.uploadImagePost(idPost, file);
    }

    @CrossOrigin("*")
    @PostMapping(
            path = "{idUser}/image/uploadVideo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadVideoPost(@PathVariable("idUser") Integer idPost,
                                @RequestParam("file") MultipartFile file) throws IOException {
        postService.uploadVideoPost(idPost, file);
    }

    @CrossOrigin("*")
    @GetMapping(path = "/landing/{idPost}/image/downloadImagePost")
    public byte[] downloadImagePost(@PathVariable("idPost") Integer idPost) {
        return  postService.downloadImage(idPost);
    }

    @PostMapping("/save")
    public Post submitPost(@RequestBody PostRequest body){
        return postService.submitPostToDB(body.getDesc(), body.getNameClub(), body.getIdUser(), body.getUserName());
    }

    @GetMapping("/getPost")
    public List<Post> retrieveAllPost(){
        List<Post> result=postService.retrivePostFromDB();
        result.sort((e1, e2) -> e2.getDateTime().compareTo(e1.getDateTime()));
        return result;
    }

    @GetMapping("/getLastPost")
    public Post retrieveLastPost(){
        return postService.getLastPost();
    }

    @GetMapping("/getCommentsSize/{postId}")
    public int retrieveCommentsSize(@PathVariable("postId") Integer postID){
        return postService.getCommentsSize(postID);
    }

    @GetMapping("/getPostById/{postId}")
    public Post getPostById(@PathVariable("postId") Integer postID){
        return postService.findPostById(postID).getBody();
    }

    @DeleteMapping("/delete/{postId}")
    public List<Post> deleteParticularPost(@PathVariable("postId") Integer postID){
        List<Post> result=postService.deletePostFromDB(postID);
        return result;
    }

    @GetMapping("/getPost/{postID}")
    public ResponseEntity<Post> retrieveAllPost(Integer postID){
        return postService.findPostById(postID);
    }
}
