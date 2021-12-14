import React, { Component, useState, useEffect} from 'react'
import axios from 'axios';
import ClubService from '../Services/ClubService';

export default class ListClubComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            club: []
        }

    }

    componentDidMount() { // method get called after the component is mounted
        ClubService.getClubs().then((res) => {
            this.setState({club: res.data});
        });
    }

    
    render() {
        return (
            <div>
                <h2 className='text-center'>Clubs list</h2>
                <div className='row'>
                    <table className='table table-striped table-bordered'>
                        <thead>
                            <tr>
                                <th>nomClub</th>
                                <th>descClub</th>
                                <th>dateCreation</th>
                                <th>status</th>
                                <th>logoLink</th>
                                <th>coverImgLink</th>
                            </tr>
                        </thead>

                        <tbody>
                            {
                                this.state.club.map(
                                    Club =>
                                    <tr key= {Club.idClub}>
                                        <td>{Club.nomClub}</td>
                                        <td>{Club.descClub}</td>
                                        <td>{Club.dateCre}</td>
                                        <td>{Club.status}</td>
                                        <td>{Club.logoClub}</td>
                                        <td>{Club.coverImgLink}</td>
                                    </tr>    
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}
