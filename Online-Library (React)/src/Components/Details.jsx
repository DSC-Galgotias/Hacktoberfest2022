import axios from 'axios'
import React, { memo, useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import './Styles/details.css'
import gif from '../loading.gif';

const Details = ({ isbn }) => {
  const [detail, setDetail] = useState([]);
  useEffect(() => {
    axios.get(`https://openlibrary.org/api/books?bibkeys=ISBN:${isbn}&jscmd=details&format=json`)
      .then(resp => {
        for (let x in resp.data)
          setDetail(resp.data[x])
      })

  }, [isbn])

  const themeSwitch = () => {
    document.querySelector('body').classList.toggle("darkTheme");
  }

  return (
    <div id="detailsContainer">
      <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", padding: "2%", backgroundColor: "var(--detailBG)", borderTopLeftRadius: "5vh", borderTopRightRadius: "5vh" }}> <button style={{ color: "white", backgroundColor: "black", border: "none", cursor: "pointer" }} onClick={() => { window.history.go(-1) }}> <i className="fa-solid fa-arrow-left"></i> Go Back </button><Link to={'/'} ><h1>Digital Library</h1></Link> <button style={{ backgroundColor: "var(--buttonBG)", color: "var(--lightblue1)", border: "none", borderRadius: "1vh" }} onClick={themeSwitch}>Change Theme</button></div>
      {detail.length === 0 ? <img src={gif} alt='loading search result' className='loading' /> :
        <>
          <div id="detailsView">
            <div className='bookImage'>
              <img src={(detail.details.covers !== undefined) ? `https://covers.openlibrary.org/b/id/${detail.details.covers}-L.jpg` : 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfYEG2AZWFMEjpExDUQUtJXaX175rgJi-5Ji-kSVO_wTHlBNr1AGAtaGz4P7j0qarq_Gg&usqp=CAU'} alt={detail.details.full_title} />
            </div>
            <div className="aboutBook">
              <h6> An edition of {detail.details.title} ({detail.details.publish_date})</h6>
              <h3>{detail.details.title}</h3>

              {detail.details.authors !== undefined && <p style={{ fontSize: "3.2vh" }}> by <span style={{ fontSize: "3.2vh", fontWeight: "700", color: "var(--colorPara)" }}>{detail.details.authors[0].name}</span></p>}
              <div style={{ backgroundColor: "var(--detailBG)", padding: "5%", marginTop: "1vh", borderRadius: "2vh" }}>
                <ul>
                  <li>Publish Date : <span> {detail.details.publish_date}</span></li>
                  <li> Publisher : <span> {detail.details.publishers}</span> </li>
                  <li>Pages : <span> {detail.details.number_of_pages}</span> </li>
                </ul>
              </div>
              <div className='buttons'>
                <a href={detail.preview_url} target="blank" style={{ backgroundColor: "white", color: "#06275c", border: ".3vh solid #06275c" }}> Preview</a>
                <a href={`https://www.google.co.in/search?q=${detail.details.title}`} style={{ backgroundColor: "green", color: "white", border: ".3vh solid" }} target="blank"> Want to read</a>
              </div>
            </div>
          </div>
        </>
      }

    </div>
  )
}

export default memo(Details)