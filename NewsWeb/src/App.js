
import './App.css';

import PropTypes from 'prop-types'
import React, { Component } from 'react'
import Navbar from './Mycomponent/Navbar';
import News from './Mycomponent/News';


export class App extends Component {


  render() {
    return (
      <>
      <Navbar />
      <News />
   
      </>
    )
  }
}

export default App