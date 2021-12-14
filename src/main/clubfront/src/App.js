import React, {useState, useEffect, useCallback} from 'react';

import './App.css';

import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import ListClubComponent from './Components/ListClubComponent';
import CreateClubComponent from './Components/CreateClubComponent';
import UpdateClubComponent from './Components/UpdateClubComponent';
import HeaderComponent from './Components/HeaderComponent';
import FooterComponent from './Components/FooterComponent';
import axios from 'axios';

import {useDropzone} from 'react-dropzone';

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

function App() {
  return (
    <div className="App">
      <Router>
        <HeaderComponent/>
        <div>
          <div>
            <Switch>
              <Route path = "/clubs" component={ListClubComponent}></Route>
              <Route path = "/add-club" component={CreateClubComponent}></Route>
              <Route path = "/update-club" component={UpdateClubComponent}></Route>
              <Route path="/aa" component={ClubProfiles} />
            </Switch>
          </div>
        </div>
        <FooterComponent/>
      </Router>
    </div>
    
  );
}

export default App;
