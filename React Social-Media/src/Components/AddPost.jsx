
import { Add } from '@mui/icons-material';
import { Avatar, Fab, Modal, TextField, Tooltip, Typography, } from '@mui/material'
import { Box, styled } from '@mui/system';
import React from 'react'
import { useState } from 'react';
import { Context } from '../App';
import Feed from './Assets/feeds.json'


const StyledModal = styled(Modal)({
  display: "flex", alignItems: "center", justifyContent: "space-between"
})

const UserBox = styled(Box)({
  display: "flex", alignItems: "center", gap: "10px", margin: "10px"
})

const AddPost = () => {
  const { details, setDetails, logger } = Context();
  const [open, setOpen] = useState(false);
  const [postTitle, setPostTitle] = useState("");
  const [postIMG, setPostIMG] = useState("");

  const handlePost = (e) => {
    setDetails([{ id: details.length + 1, by: logger.name, byID: logger.id, postTitle: postTitle, postIMG: postIMG, likes: 0, comments: [] }, ...details]);
    Feed.unshift({ id: details.length + 1, by: logger.name, byID: logger.id, postTitle: postTitle, postIMG: postIMG, likes: 0, comments: [] })
    setOpen(false);
  }

  return (
    <>
      <Tooltip title="Create Post" onClick={() => setOpen(true)} sx={{ position: "fixed", top: "85%", left: { xs: '45%', md: 30 } }}>
        <Fab color="primary" aria-label="add" >
          <Add />
        </Fab>
      </Tooltip>

      <StyledModal open={open} onClose={() => setOpen(false)} >
        <Box width={600} height={300} bgcolor={'white'} borderRadius={5} p={4} >
          <Typography variant='h5' color="gray" textAlign={'center'} > Create Post</Typography>
          <UserBox>
            <Avatar sx={{ width: 30, height: 30 }}> {logger.name.charAt(0)}</Avatar>
            <Typography variant='span' fontSize={"20px"}> {logger.name} </Typography>
          </UserBox>
          <TextField
            multiline
            rows={3}
            placeholder="What's on Your Mind?"
            variant="standard"
            name='postTitle'
            fullWidth autoFocus sx={{ marginBottom: '10px' }} onChange={(e) => setPostTitle(e.target.value)}
          />
          <input type={'file'} name='postIMG' style={{ fontSize: "16px", width: "100%", margin: "10px" }} onChange={(e) => { setPostIMG(URL.createObjectURL(e.target.files[0])) }} />
          <button style={{ width: "100%", border: "none", backgroundColor: 'dodgerblue', fontSize: "20px", fontWeight: "600", color: "white", padding: "3px", cursor: "pointer", borderRadius: "10px" }} onClick={handlePost}> Post </button>
        </Box>
      </StyledModal>
    </>
  )
}

export default AddPost

