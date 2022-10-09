import React from "react"

export default function MainComponent(props){
    return(
        <div className= {props.darkMode ? "main-content dark" : "main-content"}>
        <h1 className="content-text">Fun Facts about React</h1>
        <ul>
            <li>Was released in 2013</li>
            <li>Was originally created by Jordan Walke</li>
            <li>Has well over 100k stars on Github</li>
            <li>Is maintained by Facebook</li>
            <li>Powers thousand of enterprise apps, including mobile apps</li>
        </ul>
        </div>
    )
}