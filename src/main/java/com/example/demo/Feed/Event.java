package com.example.demo.Feed;


import javax.persistence.*;

@Entity
@Table(name = "Events")
public class Event {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="eventID", unique = true, nullable = false)
    private Integer eventID;

    @Column(name = "post_id")
    private Integer idPost;

    private String name
}
