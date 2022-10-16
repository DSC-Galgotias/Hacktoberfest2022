import { Avatar, AvatarGroup, Box, ImageList, ImageListItem, Typography } from '@mui/material'
import React from 'react'
import { Context } from '../App'
import Tippy from '@tippyjs/react'
import 'tippy.js/dist/tippy.css';
import feeds from './Assets/feeds.json'

const RightBar = () => {
  const { details, setDetails, users, muted, setMuted } = Context();

  const unMute = () => {
    setDetails(feeds);
    setMuted([])
  }

  return (
    <Box flex={1.8} sx={{ display: { xs: "none", sm: "block" } }}>

      <Box sx={{ display: "flex", flexDirection: "column", alignItems: "flex-start", gap: "10px" }}>
        <Typography variant='h5'> Friends </Typography>
        <AvatarGroup max={3}>
          {users.map((val) => <Avatar alt={val.fullName} key={val.id}> {val.name.charAt(0)} </Avatar>)}
        </AvatarGroup>
      </Box>
      <Box sx={{ display: "flex", flexDirection: "column", alignItems: "flex-start", pt: "20px" }}>
        <Typography variant='h5'> Muted Friends </Typography>
        {muted.length === 0 ? <Typography variant='p' color="gray" > No one is Muted </Typography> :
          <>
            <AvatarGroup max={2}>
              {muted.map((val) => <Avatar alt={val.fullName} key={val.id}> {val.name.charAt(0)} </Avatar>)}
            </AvatarGroup>
            <Tippy content="UnMute All">
              <i className="fa-solid fa-volume-high" style={{ fontSize: "2rem", marginTop: "50px", cursor: "pointer" }} onClick={unMute} />
            </Tippy>
          </>}
      </Box>
      <Box sx={{ mt: 5 }}>
        <Typography variant='h5'> Recent Photos</Typography>
        <ImageList cols={2} sx={{ mt: 2 }}>
          {details.map((item) => (
            <ImageListItem key={item.id}>
              <img src={item.postIMG} alt={item.postTitle} loading="lazy" width={"150px"} />
            </ImageListItem>
          ))}
        </ImageList>
      </Box>

    </Box>
  )
}

export default RightBar