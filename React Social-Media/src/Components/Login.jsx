import React from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { Context } from '../App';

const Login = () => {
  const { users, setLogger } = Context();
  const navigate = useNavigate();

  var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  var flag = true;

  const log = () => {
    const email = document.querySelector("#email").value;
    const password = document.querySelector("#password").value;
    const uname = document.querySelector("#uname").value;
    if (email === "" || password === "" || uname === "") {
      document.querySelector("#warning").innerHTML = "Please fill in details to sign in."
      document.querySelector("#warning").style = "color:red;font-size:3vh;text-align:center;margin:2vh 0;"
    } else if (validRegex.test(email) === false) {
      document.querySelector("#warning").innerHTML = "Please fill valid email."
      document.querySelector("#warning").style = "color:red;font-size:3vh;text-align:center;margin:2vh 0;"
    }
    else {
      document.querySelector("#warning").style = "display:none";
      users.forEach((val) => {
        if ((val.email).toUpperCase() === email.toUpperCase() && (val.pass).toUpperCase() === password.toUpperCase() && (val.name).toUpperCase() === uname.toUpperCase()) {
          setLogger({ id: val.id, name: val.name })
          navigate('/feed');
          flag = false;
        }
      })
      if (flag) {
        document.querySelector("#warning").innerHTML = "No credentials match found. Try Again"
        document.querySelector("#warning").style = "color:red;font-size:3vh;text-align:center;margin:1vh 0;"
      }
    }
  }

  return (
    <div id='signContainer'>
      <Link to="/" style={{ fontSize: "5vh", textDecoration: "none", color: "#0a66c2", fontWeight: "700" }}>Connect <span style={{ backgroundColor: "#0a66c2", color: "white", padding: ".2vh .5vh", borderRadius: "1vh" }}>ing</span></Link>
      <h1 style={{ textAlign: "center", fontSize: "5vh", paddingTop: "4vh", fontWeight: "100" }}>Stay updated on your professional world</h1>
      <div id='loginFormContainer'>
        <h2 style={{ textAlign: "center" }}>LOG In</h2>
        <div style={{ width: "80%", margin: "auto", marginTop: "5vh" }}>
          <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Enter your Email</h6>
          <input type="email" required id='email' autoFocus />
          <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Enter your User Name</h6>
          <input type="text" required id='uname' />
          <h6 style={{ margin: "1vh 0", fontSize: "2.5vh", color: "grey" }}>Password (3 or more characters)</h6>
          <input type="password" required id='password' />
        </div>
        <p id='warning'></p>
        <button style={{ width: "80%", margin: "3% 10%", fontSize: "4vh", backgroundColor: "#0a66c2", color: "white", padding: "2vh 4vh", borderRadius: "5vh", cursor: "pointer", border: "none" }} onClick={log}>Sign in</button>
        <Link to='/signup' style={{ fontSize: "2.5vh", color: "grey", fontWeight: "600", textAlign: "center", textDecoration: "none", cursor: "default" }} > <h3>Not on Connecting? <span style={{ color: "#0a66c2", cursor: "pointer" }}>Join now</span></h3></Link>
      </div>
    </div>
  )
}

export default Login