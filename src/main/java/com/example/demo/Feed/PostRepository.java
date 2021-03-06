package com.example.demo.Feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findTopByOrderByDateTimeDesc();

}
