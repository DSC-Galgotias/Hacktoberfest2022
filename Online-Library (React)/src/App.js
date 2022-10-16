
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Home from './Components/Home';
import Details from './Components/Details';
import SearchPage from './Components/SearchPage';
import { useState } from 'react';

function App() {

  const [text,setText] = useState('');
  const [isbn,setISBN] = useState('');

  return ( 
    <>
      <div id='topContainer'>
        <Router>
          <Routes>
            <Route path='/' exact element={<Home text={text} setText={setText}/>} />
            <Route path='/search'  element={<SearchPage text={text} setText={setText} setISBN={setISBN} />} />
            <Route path='/details'  element={<Details isbn={isbn}/>} />
          </Routes>
        </Router>
      </div>
    </>
  );
}

export default App;
