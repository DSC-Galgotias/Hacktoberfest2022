import Button from 'react-bootstrap/Button';
import React, { Component,useState }  from 'react';  
import { Card, FloatingLabel, Form, FormControl } from 'react-bootstrap';
import { createPost } from '../Web3Client';
import 'bootstrap/dist/css/bootstrap.min.css';


const CreatePost = () => {

    const [message, setMessage] = useState('');

    const handleMessageChange = event => {
      setMessage(event.target.value);
      console.log(event.target.value);
    };

    const post = ()=>{
        if(message.length != 0)
        createPost(message);
    }

    return (
        <div>            
        <Card style={{backgroundColor:"#1B2730",
        borderRadius:"8px", textAlign:"left", padding:"8px",margin:"8px",
        boxShadow:"0 4px 8px 0 rgba(0,0,0,0.2)",
        transition:" 0.3"}}>
                <FloatingLabel controlId="floatingTextarea2" style={{padding:"16px", borderRadius:"16px"}}>
                    <FormControl
                    as="textarea"
                    placeholder="Write a post ..."
                    value={message}
                    onChange={handleMessageChange}
                    style={{ height:"50px", width:"90%", color:"white",
                    resize:"none",overflow:"hidden",
                    margin:"auto",
                    borderRadius:"16px", padding:"16px 16px",backgroundColor:"#28343E",
                    borderColor:"#28343E",
                fontSize:"1em"}}
                    />
                </FloatingLabel>
                <Button onClick={post} style={{padding:"8px 8px", backgroundColor:"#28343E",
                width:"20%",margin:"auto",
                fontSize:"1em",color:"white",
            borderRadius:"16px"}}>Post</Button>
            </Card>
        </div>
    )

}

export default CreatePost