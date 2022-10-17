import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Styles/SearchPage.css'


const SearchCard = ({ val, setISBN }) => {
    const Navigate = useNavigate();
    const detail = () => {
        if (val.isbn !== undefined) {
            setISBN(val.isbn[0]);
            Navigate("/details");
        }else{
            alert("Sorry, this book details are not available")

        }
    }

    return (
        <div className="cardContainer">
            <div className="coverImage">
                <img src={`https://covers.openlibrary.org/b/olid/${val.cover_edition_key}-M.jpg`} alt={val.title} />
            </div>
            <div className="bookDetails">
                <h2>{val.title}</h2>
                <li>by {" " + val.author_name} </li>
                <li><span>First published in {val.first_publish_year}</span> </li>
                <li><span> {val.edition_count} editions in {val.language === undefined ? 0 : val.language.length} languages  - {val.ebook_count_i} previewable</span> </li>
            </div>
            <div className="actions">
                <button onClick={detail}> Know More </button>
            </div>
        </div>
    )
}

export default SearchCard;
