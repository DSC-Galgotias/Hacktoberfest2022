import styled from '@emotion/styled'
import { Close, Logout, Mail, Notifications, Settings } from '@mui/icons-material';
import { AppBar, Avatar, AvatarGroup, Badge, Divider, InputBase, ListItemIcon, Menu, MenuItem, Toolbar, Tooltip, Typography } from '@mui/material'
import { Box } from '@mui/system';
import Tippy from '@tippyjs/react';
import React from 'react'
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'
import { Context } from '../App';
import feeds from './Assets/feeds.json'


const StyledToolBar = styled(Toolbar)({
  display: "flex", justifyContent: "space-between"
});

const Search = styled('div')(({ theme }) => ({
  backgroundColor: "white", padding: "0 10px", borderRadius: "5px", width: "40%"
}))
const Icons = styled(Box)(({ theme }) => ({
  display: "none",
  alignItems: "center",
  gap: "20px",
  [theme.breakpoints.up("sm")]: {
    display: "flex",
  },
}));

const UserBox = styled(Box)(({ theme }) => ({
  display: "flex",
  alignItems: "center",
  gap: "10px",
  [theme.breakpoints.up("sm")]: {
    display: "none",
  },
}));


const FeedNav = () => {
  const navigate = useNavigate();
  const [openMenu, setOpenMenu] = useState(false)
  const { logger, setLogger, muted, setMuted, setDetails } = Context();
  const logout = () => {
    navigate('/')
    setLogger({})
  }
  const unMute = () => {
    setDetails(feeds);
    setMuted([])
  }
  return (
    <AppBar position="sticky" >
      <StyledToolBar>

        {/* Logo  */}

        <Typography variant='h1' sx={{ display: { xs: "none", sm: "block" }, fontSize: "24px" }}>
          <Link to={'/'} style={{ textDecoration: "none", color: "white" }}>
            Connect<span>ing</span></Link>
        </Typography>
        <Typography variant='h1' sx={{ display: { xs: "block", sm: "none" }, fontSize: "24px" }}>
          <Link to={'/'} style={{ textDecoration: "none", color: "white" }}>
            <span>ing</span></Link>
        </Typography>

        {/* Seachbar */}
        <Search><InputBase placeholder='search' fullWidth /></Search>

        {/* Side Icons */}
        <Icons>
          <Tippy content="logout"><Logout color="action" sx={{ cursor: "pointer" }} onClick={logout} /></Tippy>
          <Badge badgeContent={4} color="error" sx={{ cursor: "pointer" }}>
            < Mail color="action" />
          </Badge>
          <Badge badgeContent={4} color="error" sx={{ cursor: "pointer" }}>
            <  Notifications color="action" />
          </Badge>
          <Avatar src='https://image.shutterstock.com/image-photo/young-handsome-man-beard-wearing-260nw-1768126784.jpg' sx={{ width: 30, height: 30, cursor: "pointer" }} />
        </Icons>
        <UserBox >
          <Tooltip title='Account settings'>
            <>
              <Avatar src='https://image.shutterstock.com/image-photo/young-handsome-man-beard-wearing-260nw-1768126784.jpg' sx={{ width: 30, height: 30, cursor: "pointer" }} onClick={() => setOpenMenu(true)} /> <Typography variant='span'> {logger.name}</Typography>
            </>
          </Tooltip>
        </UserBox>
      </StyledToolBar>
      <Menu
        open={openMenu}
        onClose={() => setOpenMenu(false)}
        transformOrigin={{ horizontal: 'right', vertical: 'top' }}
        anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
        PaperProps={{
          elevation: 0,
          sx: {
            overflow: 'visible',
            filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
            mt: 1.5,
            height: 450,
            '& .MuiAvatar-root': {
              width: 32,
              height: 32,
              ml: -0.5,
              mr: 1,
            },
            '&:before': {
              content: '""',
              display: 'block',
              position: 'absolute',
              top: 0,
              right: 14,
              width: 10,
              height: 10,
              bgcolor: 'background.paper',
              transform: 'translateY(-50%) rotate(45deg)',
              zIndex: 0,
            },
          },
        }}
      >
        <MenuItem>
          <Avatar src='https://image.shutterstock.com/image-photo/young-handsome-man-beard-wearing-260nw-1768126784.jpg' sx={{ width: 30, height: 30, cursor: "pointer" }} /> <Typography variant='span'> {logger.name}</Typography>
        </MenuItem>
        <MenuItem>
          <Avatar /> My account
        </MenuItem>
        <Divider />
        <MenuItem>
          <ListItemIcon>
            <i className="fa-solid fa-volume-xmark" fontSize='medium' />
          </ListItemIcon>
          {muted.length === 0 ? <Typography variant='p' color="gray" > No one is Muted </Typography> :
            <AvatarGroup max={2}>
              {muted.map((val) => <Avatar alt={val.fullName} key={val.id}> {val.name.charAt(0)} </Avatar>)}
            </AvatarGroup>}
        </MenuItem>
        {muted.length > 0 && <MenuItem onClick={unMute}>
          <ListItemIcon>
            <i className="fa-solid fa-volume-high" fontSize='medium' />
          </ListItemIcon>
          Unmute All
        </MenuItem>}
        <MenuItem>
          <ListItemIcon>
            <Settings fontSize="small" />
          </ListItemIcon>
          Settings
        </MenuItem>
        <MenuItem onClick={logout}>
          <ListItemIcon>
            <Logout fontSize="small" />
          </ListItemIcon>
          Logout
        </MenuItem>
        <MenuItem onClick={() => setOpenMenu(false)}>
          <ListItemIcon>
            <Close fontSize="small" />
          </ListItemIcon>
          Close
        </MenuItem>
      </Menu>
    </AppBar>
  )
}

export default FeedNav