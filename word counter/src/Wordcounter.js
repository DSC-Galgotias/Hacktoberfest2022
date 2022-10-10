import React, { useState } from 'react';
import Darkblue from "./Darkblue.jpg"

export const Wordcounter = () => {
    const [text, setText] = useState('');

    const handleup = () => {
        let newtext = text.toUpperCase();
        setText(newtext)

    }
    const handledown = () => {
        let newtext = text.toLowerCase();
        setText(newtext)

    }

    const handleonchange = (event) => {
        setText(event.target.value)
    }
    const handlecopy = () => {
        var txt = document.getElementById("text-container");
        txt.select();
        navigator.clipboard.writeText(text.value);

    }
    return (
        <>

            <div className='All'>
            <img className='backimg' src={Darkblue} />
            <div className='counter-text'>
                <div className='container'>
                    <h1 className='texthead whitetext'>Word Counter</h1>
                    <div className='text-container whitetext'>
                        <textarea className='inputext whitetext' value={text} onChange={handleonchange} placeholder='Enter Text....' rows='8' ></textarea>
                    </div>
                   
                    <button className='inputext-upbtn mx-3 buttons' onClick={handleup}>Convert to Uppercase</button>
                    <button className='inputext-downbtn buttons' onClick={handledown}>Convert to Lowercase</button></div>
                   

           

                <div className='container-2'>
                    <h3 className='whitetext'>Your Text Summary</h3>
                    <p className='whitetext'><b>{text.split(" ").length} </b>words and <b>{text.length} </b>alphabets</p>
                    <p className='whitetext'><b>{((0.08 * text.split(" ").length) / 60) * 100}</b> Minutes Read </p>
                    <h4 className='whitetext'>Preview</h4>
                    <p className='whitetext'>{text}</p>

                </div>
            </div>

            </div>



        </>
    )
}







