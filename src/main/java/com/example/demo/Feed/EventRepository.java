package com.example.demo.Feed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<ClubEvent, Integer> {

    ClubEvent findTopByOrderByDateTimeDesc();

    ClubEvent findByIdPost(Integer idPost);
}
