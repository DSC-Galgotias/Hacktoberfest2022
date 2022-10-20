import logo from './logo.svg';
import React, { Component, useEffect, useState } from 'react';
import './App.css';
import CreatePost from './components/createPost';
import AppName from './components/navbar';
import HomePage from './components/homePage';

function App() {

  const [blogs,setBlogs] = useState();

  return <div className='App'>
    <div style={{backgroundColor:"#05141C"}}>
      <HomePage/>
    </div>
  </div>
}

export default App;
