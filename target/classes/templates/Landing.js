import React, {useState, useEffect, useCallback} from 'react';
import { Link } from "react-router-dom";

import "./Landing.css"
import axios from 'axios';


import {useDropzone} from 'react-dropzone';
import ClubService from '../Services/ClubService';


const ClubProfiles = () => {
    const [ClubProfiles, setClubProfiles] = useState([]);
  
    const fetchClubProfiles = () => {
      axios.get("http://localhost:8080/api/club").then(res => {
        console.log(res);
        setClubProfiles(res.data);
      });
    }
  
    useEffect( () => {
      fetchClubProfiles();
    }, []);
  
    return ClubProfiles.map((clubProfiles, index) => {
  
      return (
        <div key={index}>
          {clubProfiles.idClub ? (
            <img src={'http://localhost:8080/api/'+ clubProfiles.idClub +'/image/download'} ></img>
          ) : null }
          <h1>{clubProfiles.nomClub}</h1>
          <ul>
            <li>id : {clubProfiles.idClub}</li>
            <li>descrption : {clubProfiles.descClub}</li>
            <li>date de creation : {clubProfiles.dateCre}</li>
            <li><Dropzone {...clubProfiles} /></li>
          </ul>
          <br></br>
          <br></br>
          <br></br>
        </div>
      )
    })
  };
  
  function Dropzone({ idClub }) {
    const onDrop = useCallback(acceptedFiles => {
      const file = acceptedFiles[0];
      console.log(file);
  
      const formData = new FormData();
      formData.append("file", file);
      
      axios.post(
        'http://localhost:8080/api/'+ idClub +'/image/upload', 
        formData,
        {
          headers: {
            "Content-Type" : "multipart/form-data"
          }
        }
      )
      .then(() => {
        console.log("file  uploaded successfully");
      })
      .catch(err => {
        console.log(err);
      });
      
    }, [])
    const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
  
    return (
      <div {...getRootProps()}>
        <input {...getInputProps()} />
        {
          isDragActive ?
            <p>Drop the files here ...</p> :
            <p><button>Update club logo</button></p>
        }
      </div>
    )
  }

const Landing = () => {
    return (
        <div className='landing'>
            <Link to="/home" >Login</Link>
            <ClubProfiles/>
        </div>
    )
}

export default Landing
