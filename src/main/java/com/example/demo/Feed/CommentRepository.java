package com.example.demo.Feed;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    ArrayList<Comment> findAllByIdPost(Integer postID);
}
