import { ExpandLess, ExpandMore, MoreVert, Share } from '@mui/icons-material'
import { Avatar, Card, CardActions, CardContent, CardHeader, CardMedia, Collapse, IconButton, List, ListItem, ListItemButton, ListItemText, TextField, Typography } from '@mui/material'
import Tippy from '@tippyjs/react'
import React, { useState } from 'react'
import { Context } from '../App'
import 'tippy.js/dist/tippy.css';

const FeedsCard = ({ val }) => {
    // Receiving Context
    const { users, details, setDetails, logger, muted, setMuted } = Context()

    // State to open Collapse of card for comments
    const [colOpen, setColOpen] = useState(false);
    // State for Comment
    const [comment, setComment] = useState("");

    // State for Likes
    const [liked, setLiked] = useState(false)

    // Edit comment function
    const editComment = (e) => {
        details.forEach((val2) => {
            if (val2.id === val.id) {
                setComment(Object.values(val.comments[e.target.id]))
            }
        })
        val.comments.splice(e.target.id, 1)
    }
    // Delete function
    const delComment = (e) => {
        details.forEach((val2) => {
            if (val2.id === val.id) {
                val.comments.splice(e.target.id, 1)
            }
        })
        setComment(" ");
    }
    // Comment function
    const commented = (e) => {
        if (e.key === 'Enter') {
            details.forEach((val2) => {
                if (val2.id === val.id) {
                    const obj = {}
                    obj[logger.name] = comment;
                    val.comments.push(obj);
                    setComment("");
                }
            })
        }
    }

    // Function for Likes 
    const like = () => {
        if (liked) {
            details.forEach((val2) => {
                if (val2.id === val.id)
                    val.likes--;
            })
            setLiked(false)
        } else {
            details.forEach((val2) => {
                if (val2.id === val.id)
                    val.likes++;
            })
            setLiked(true)
        }
    }

    // To mute specific persons post
    const muteThis = (e) => {
        setMuted([...muted, ...users.filter((val2) => val2.id === val.byID)])
        setDetails(details.filter((val3) => val3.byID !== val.byID))
    }

    return (
        <Card sx={{ Width: 450, marginTop: "2rem", boxShadow: "rgba(0, 0, 0, 0.35) 0px 5px 15px" }}>
            <CardHeader
                avatar={
                    <Avatar >
                        {val.by.charAt(0)}
                    </Avatar>
                }
                action={
                    <IconButton aria-label="settings">
                        {logger.name.toLowerCase() !== (val.by).toLowerCase() && <Tippy content={<i className="fa-solid fa-volume-xmark" id={val.byID} style={{ fontSize: "1.2rem", color: "white" }} onClick={muteThis} />} interactive={true} interactiveBorder={500} delay={10} >
                            <MoreVert />
                        </Tippy>}
                    </IconButton>
                }
                title={val.by === logger.name ? "You" : val.by}
            />
            {val.postIMG && <CardMedia component="img" height="20%" image={val.postIMG} alt={val.postTitle} />}
            <CardContent>
                <Typography variant="h6" color="text.primary">
                    {val.by}: {val.postTitle}
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
                <IconButton aria-label="Likes" onClick={like}>
                    {liked ? <i className="fa-solid fa-heart" style={{ color: "red" }} /> : <i className="fa-solid fa-heart" style={{ color: "grey" }} />} {val.likes}
                </IconButton>
                <IconButton aria-label="share">
                    <Share />
                </IconButton>
                {colOpen ? <ExpandLess aria-label="show more" onClick={() => { setColOpen(!colOpen) }} expand={colOpen.toString()} sx={{ cursor: "pointer" }} /> : <ExpandMore aria-label="show more" onClick={() => { setColOpen(!colOpen) }} expand={colOpen.toString()} sx={{ cursor: "pointer" }} />}
            </CardActions>
            <Collapse timeout="auto" in={colOpen} unmountOnExit >
                {/* Comments list in Collapse */}
                <CardContent>
                    <List>
                        {val.comments.map((val1, index) => {
                            return <ListItem key={index} >
                                <ListItemText sx={{ width: "70%" }} primary={`${Object.keys(val1)[0]} : ${Object.values(val1)[0]} `} />
                                {Object.keys(val1)[0].toLowerCase() === logger.name.toLowerCase() && <><ListItemButton><i className="fa-solid fa-pen-to-square" onClick={editComment} id={index} style={{ fontSize: "1.3rem" }} /> </ListItemButton> <ListItemButton ><i className="fa-solid fa-trash" onClick={delComment} id={index} style={{ fontSize: "1.3rem" }} /></ListItemButton></>}
                            </ListItem>
                        })}

                        {/* Text Field for comment */}
                        <TextField id="outlined-basic" label="Comment" variant="outlined" sx={{ width: "90%", fontSize: "1rem", borderRadius: "5px", marginLeft: "1rem" }} autoFocus onChange={(e) => { setComment(e.target.value) }} value={comment} onKeyPress={commented} />
                    </List>
                </CardContent>
            </Collapse>
        </Card>
    )
}

export default FeedsCard