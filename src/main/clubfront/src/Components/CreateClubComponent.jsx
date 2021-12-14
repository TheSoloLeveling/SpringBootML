import React, { Component } from 'react'
import ClubService from '../Services/ClubService';


export default class CreateClubComponent extends Component {

    
    constructor(props) {
        super(props)
        
        this.state = {
            nomClub: '',
            descClub: '',

        }
        this.changeClubNameHandler = this.changeClubNameHandler.bind(this);
        this.changeClubDescHandler = this.changeClubDescHandler.bind(this);
        this.saveClub = this.saveClub.bind(this);
    }

    saveClub = (e) => {
        e.preventDefault();
        let club = {nomClub: this.state.nomClub, descClub: this.state.descClub};
        console.log('club => ' + JSON.stringify(club));

        ClubService.createClub(club).then(res => {
            this.props.history.push('/clubs');
        });
    }

    changeClubNameHandler = (event) => {
        this.setState({nomClub: event.target.value})
    }

    changeClubDescHandler = (event) => {
        this.setState({descClub: event.target.value})
    }

    cancel() { 
        this.props.history.push('/clubs');
    }
    

    render() {
        return (
            <div>
                <div className='container'>
                    <div className='row'>
                        <div className=''>
                            <h3 className=''>Add Club</h3>
                            <div className=''>
                                <form>
                                    <div className=''>
                                        <label> Club Name: </label>
                                        <input  placeholder='Club Name' name='nomClub' className=''
                                            value={this.state.nomClub} onChange={this.changeClubNameHandler}>
                                        </input>
                                    </div>
                                    <div className=''>
                                        <label> Club Description: </label>
                                        <input  placeholder='Club Desc' name='descClub' className=''
                                            value={this.state.descClub} onChange={this.changeClubDescHandler}>
                                        </input>
                                    </div>

                                    <button className='' onClick={this.saveClub}>Save Club</button>
                                    <button className='' onClick={this.cancel.bind(this)}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        )
    }
}


