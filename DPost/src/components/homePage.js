import React,{useState,useEffect} from "react";
import Post from "./post";
import { getAllPosts, getCount} from "../Web3Client";
import CreatePost from '../components/createPost';
import 'bootstrap/dist/css/bootstrap.min.css';


function makeid() {
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  
    for (var i = 0; i < 5; i++)
      text += possible.charAt(Math.floor(Math.random() * possible.length));
  
    return text;
  }

const HomePage = ()=>{
    
    const [count,setCount] = useState();
    const [state,setState] = useState();  
    const [arr,setArr] = useState([]);


    getCount().then((res)=>{
        setCount(res);
    }).catch(e=>console.log(e))

    useEffect( ()=>{
       getAllPosts().then((res)=>{
           setState(res);
       }).catch(e=>console.log(e))
    },[])


    if(state){
        for (var i = 0; i <count; i++){
            arr[count-i-1]={
                index:count-i-1,
                text:state[i][0],
                time:state[i][1],
                userId:state[i][2],
                likeCount:state[i][3],
                likeBy:state[i][4]
            }
        }
    }
    if(arr.length)
    console.log(arr); 
    
    return (
        <div style={{width:"45%",margin:"auto",backgroundColor:"#05141C", 
        color:"white",
        minHeight:"100vh",padding:"16px"}}>
            <CreatePost/>
            
            {/* <div style={{height:"auto",fontSize:"28px",
            padding:"4px",borderRadius:"16px",
        fontFamily:"cursive"}}>
            Home</div> */}
            {
                arr.length>0 ? arr.map((data)=>(
                    <Post index={data.index} text={data.text} time={data.time} userId={data.userId} userName={makeid()}
                    likeCount={data.likeCount} likeBy={data.likeBy}/>
                )) : null
            }
        </div>
    )
}

export default HomePage