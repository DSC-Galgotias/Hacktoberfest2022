import React from "react"
import Header from "./components/Header"
import MainComponent from "./components/MainContent"
import "./style.css"

export default function App() {
    const [darkMode, setDarkMode] = React.useState(true)
    function toggleDarkMode() {
        setDarkMode(prevMode => !prevMode)
    }
    return (
        <>
            <div id="container">
                <Header darkMode={darkMode}
                toggle ={toggleDarkMode} />
                <MainComponent darkMode={darkMode}/>
            </div>

        </>
    )
}