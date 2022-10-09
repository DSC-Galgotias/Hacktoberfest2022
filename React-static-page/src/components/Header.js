import React from "react"
import logo from "../images/logo.png"

export default function Header(props) {
    return (
        <nav className={props.darkMode ? "dark" : ""}>
            <div className="left-side">

                <img src={logo} width="40px" alt="IDK"></img>
                <h1>React Facts</h1>
            </div>
            <div className="toggler">
                <p className="toggler--light">Light</p>
                <div className="toggler--slider" onClick={props.toggle} >
                    <div className="toggler--slider--circle"></div>
                </div>
                <p className="toggler--dark">Dark</p>
            </div>
        </nav>
    )
}