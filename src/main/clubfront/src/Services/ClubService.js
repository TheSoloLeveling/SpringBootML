import axios from 'axios';



class ClubService {

    getClubs() {
        return axios.get("/api/club");
    }

    createClub(club) {
        return axios.post("/api/createClub", club);
    }

    
}

export default new ClubService()