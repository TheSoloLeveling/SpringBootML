package com.example.demo.repositories;

import com.example.demo.Feed.Comment;
import com.example.demo.entities.ClubFollowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubFollowedRepository extends JpaRepository<ClubFollowed, Integer> {
}
