import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { Context } from '../App';

const Sign = () => {
  const { users, setUsers } = Context();
  const navigate = useNavigate();
  var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;


  const signin = () => {
    const email = document.querySelector("#email").value;
    const password = document.querySelector("#password").value;
    const uname = document.querySelector("#uname").value;
    const name = document.querySelector("#name").value;

    if (email === "" || password === "" || uname === "" || name === "") {
      document.querySelector("#warning").innerHTML = "Please fill in all details to sign up."
      document.querySelector("#warning").style = "color:red;font-size:3vh;text-align:center;"
    } else if (validRegex.test(email) === false) {
      document.querySelector("#warning").innerHTML = "Please fill valid email."
      document.querySelector("#warning").style = "color:red;font-size:3vh;text-align:center;"
    }
    else {
      document.querySelector("#warning").style = "display:none";
      const id = users.length;
      const obj = { "id": id, "name": uname, "fullName": name, "email": email, "pass": password };
      setUsers([...users, obj])
      navigate('/login');
      document.querySelector("#email").value = "";
      document.querySelector("#password").value = "";
      document.querySelector("#uname").value = "";
      document.querySelector("#name").value = "";
      document.querySelector("#number").value = "";
    }
  }

  return (
    <>
      <div id='signContainer'>
        <Link to="/" style={{ fontSize: "5vh", textDecoration: "none", color: "#0a66c2", fontWeight: "700" }}>Connect <span style={{ backgroundColor: "#0a66c2", color: "white", padding: ".2vh .5vh", borderRadius: "1vh" }}>ing</span></Link>
        <div id='signFormContainer'>
          <h2 style={{ textAlign: "center" }}>Register</h2>
          <div style={{ width: "80%", margin: "auto", marginTop: "2vh" }}>
            <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Your Full Name</h6>
            <input type="text" required id='name' autoFocus />
            <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Enter your User Name</h6>
            <input type="text" required id='uname' />
            <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Enter your Email</h6>
            <input type="email" required id='email' />
            <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Password (3 or more characters)</h6>
            <input type="password" required id='password' />
            <p style={{ fontSize: "2vh", margin: "1vh 2.5vh", color: "grey" }}>By clicking Agree & Join, you agree to the Connecting User Agreement, Privacy Policy, and Cookie Policy.</p>
          </div>
          <p id='warning'></p>
          <button style={{ width: "80%", margin: "1vw  10%", fontSize: "3vh", backgroundColor: "#0a66c2", color: "white", padding: "2vh 5vh", borderRadius: "5vh", cursor: "pointer", border: "none" }} onClick={signin}>Agree & Join </button>
          <Link to='/login' style={{ fontSize: "2.5vh", color: "grey", fontWeight: "600", textAlign: "center", textDecoration: "none", cursor: "default" }} > <h3>Already on Connecting? <span style={{ color: "#0a66c2", cursor: "pointer" }}>Login now</span></h3></Link>
        </div>
      </div>
    </>
  )
}

export default Sign