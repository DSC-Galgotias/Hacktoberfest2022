import axios from 'axios';
import React, { memo, useEffect, useState } from 'react'
import { Link } from 'react-router-dom';
import SearchCard from './SearchCard';
import './Styles/SearchPage.css';
import gif from '../loading.gif';


var error = "";
const SearchPage = ({ text, setText, setISBN }) => {
  var [result, setResult] = useState([]);

  useEffect(() => {
    if (text === "") {
      setResult([])
    } else {
      axios.get(`https://openlibrary.org/search.json?q=${text}&mode=ebooks&has_fulltext=true&limit=10`)
        .then(resp => {
          setResult(resp.data);
        })
        .catch((e) => {
          error = e.code;
        })
    }
  }, [text])
  const themeSwitch = () => {
    document.querySelector('body').classList.toggle("darkTheme");
  }

  return (
    <div id="searchContainer">
      <div id="searchPageContainer">
        <Link to={'/'} className="link"><h1>Digital Library</h1></Link>
        <button style={{ backgroundColor: "var(--buttonBG)", color: "var(--lightblue1)", border: "none", borderRadius: "1vh" }} onClick={themeSwitch}>Change Theme</button>
        <input type="text" value={text} placeholder="Search here" autoFocus onChange={(e) => setText(e.target.value)} />
      </div>

      {result.length === 0 ? text === "" ? <><div className='searching'><img src='https://www.pngall.com/wp-content/uploads/8/Magnifying-Glass-Search-PNG-Image-HD.png' alt='type to search' /> <h1 > * Please type anything to search</h1></div></> : error === "" ? <img src={gif} alt='loading search result' className='loading' /> : error :
        <div id="searchListContainer">
          <h1 >Search Result</h1>
          <div id='filters'>
            <span>{result.numFound > 10 ? "Top " + 10 : result.numFound} results</span><span>Relevance</span><span>Most Editions</span><span>First Published</span><span>Most Recent</span><span>Random</span>
          </div>
          <div id="searchCardContainer">
            {result.docs.map((val, index) => {
              return (
                <SearchCard val={val} key={index} setISBN={setISBN} />
              )
            })
            }
          </div>
        </div>
      }

    </div>
  )
}

export default memo(SearchPage)