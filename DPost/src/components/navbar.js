import React, { Component }  from 'react';  
import {Navbar, Container } from 'react-bootstrap';

const AppName = () => {
    return (
        <Navbar>
        <Container>
          <Navbar.Brand href="#home" style={{left:"0px",position:"fixed",margin:"8px"}}>
            <img
              alt=""
              src="https://pbs.twimg.com/profile_images/2903586302/cae0cb32f4c2347b012d609d7ac2d124_400x400.png"
              width="50"
              height="50"
              className="d-inline-block align-top"
            />{' '}
          </Navbar.Brand>
        </Container>
      </Navbar>
    )
}

export default AppName