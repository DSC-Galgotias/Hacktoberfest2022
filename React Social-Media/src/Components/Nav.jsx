import React from 'react'
import { NavLink } from 'react-router-dom';
import './style.css';

const Nav = () => {
  return (
    <>
      <nav id='nav'>
        <NavLink to='/' id="logo">Connect <span style={{ backgroundColor: "#0a66c2", color: "white", padding: ".2vh .5vh", borderRadius: "1vh" }}>ing</span></NavLink>
        <div><NavLink to='/login' id='signIN'> Sign In </NavLink>
          <NavLink to='/signup' style={{ padding: "1.5vh 3vh", border: ".2vh solid #0a66c2", borderRadius: "5vh" }} id="signup">Join now</NavLink></div>
      </nav>
    </>
  )
}

export default Nav;
