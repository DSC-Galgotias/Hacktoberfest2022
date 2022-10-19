import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Styles/Home.css';

const Home = ({ text, setText }) => {
    const Navigate = useNavigate()
    const redirect = (e) => {
        if (e.key === 'Enter') {
            Navigate("/search");
        }
    }
    const themeSwitch = ()=>{
            document.querySelector('body').classList.toggle("darkTheme");
    }
    return (
        <div id="homeContainer">
            <button style={{float:"right",backgroundColor:"var(--buttonBG)",color:"var(--lightblue1)",border:"none",borderRadius:"1vh"}} onClick={themeSwitch}>Change Theme</button>
            <div>
                <div id='homeDetailContainer'>
                    <div>
                        <h1>digital Library</h1>
                        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Veniam quidem fuga dignissimos natus aperiam praesentium non temporibus</p>
                    </div>
                    <div className='searchField'>
                        <i className="fa-solid fa-magnifying-glass"></i>
                        <input type='text' placeholder="Search by books name and press enter" className='search' autoFocus onChange={(e) => setText(e.target.value)} onKeyPress={redirect} />
                    </div>
                </div>
                
                <img src="https://cdni.iconscout.com/illustration/premium/thumb/online-library-4349388-3613876.png" alt="Home page side design" />
            </div>
        </div>
    )
}

export default Home