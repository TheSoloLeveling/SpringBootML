package com.example.demo.Feed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Status saveStatus(Status status) {

        Date date=new Date();
        long time=date.getTime();
        Timestamp dateTime=new Timestamp(time);

        status.setStatusID(UUID.randomUUID());
        status.setUploadTIme(dateTime);
        return  statusRepository.save(status);
    }

    public ArrayList<Status> getAllStatus(){
        ArrayList<Status> result=new ArrayList<Status>();
        result= (ArrayList<Status>) statusRepository.findAll();
        result.sort((e1, e2) -> e2.getUploadTIme().compareTo(e1.getUploadTIme()));
        return result;
    }
}
