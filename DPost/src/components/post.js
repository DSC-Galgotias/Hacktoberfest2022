import React,{ useState } from "react";
import { Card,Button, Badge } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import { likePost } from "../Web3Client";

const Post = ({index,text,time,userId,userName,likeCount,likeBy})=>{

    const [isClick, setClick] = useState(true);
    const [like,setLike] = useState(likeCount);

    likeBy.forEach(item => {
      if(item == userId){
        setClick = false
      }
    })

    console.log(index)
    const handleClick = () =>{
      setClick(false)
      const l = parseInt(likeCount) + 1;
      likePost(index,userId)
      setLike(l)
    }

    console.log(isClick);


    var date = new Date(time*1000)
    console.log(date)
    const t = date.getDate()+
            "/"+(date.getMonth()+1)+
            "/"+date.getFullYear()+
            " "+date.getHours()+
            ":"+date.getMinutes();


         
    
    return (
        <Card style={{backgroundColor:"#1B2730",
        borderRadius:"8px", textAlign:"left", padding:"px",margin:"16px",
        boxShadow:"0 4px 8px 0 rgba(0,0,0,0.2)",
        transition:" 0.3"}}>
            <Card.Body>
              <div style={{fontSize:"1em"}}>{userName}</div>
              <div style={{fontSize:"1em", color:"grey"}}>{t}</div>
              <br></br>
              <div style={{fontSize:"1.25em", marginTop:"8px"}}>{text}</div>
            <br></br>
            {
              isClick ? <button class="btn" onClick={handleClick} style={{color:"white"}}><i class="fa fa-heart"></i> {like}</button> :
              <button class="btn" style={{color:"red"}}><i class="fa fa-heart"></i> {like}</button>
            }
            </Card.Body>           
        </Card>
    )
}

export default Post