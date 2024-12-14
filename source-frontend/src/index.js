import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import MainPage from './screens/MainPage';
import LocalGame from './screens/LocalGame';
import SoloGame from './screens/SoloGame';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route path='/localgame' element={<LocalGame/>}/>
        <Route path='/sologame' element={<SoloGame/>}/>
      </Routes>
    </Router>
  </React.StrictMode>
);

