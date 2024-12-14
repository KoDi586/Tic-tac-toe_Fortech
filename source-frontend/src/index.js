import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import MainPage from './screens/MainPage';
import LocalGame from './screens/LocalGame';
import SoloGame from './screens/SoloGame';
import PrivateRoute from './components/PrivateRoute';
import Multiplayer from './screens/Multiplayer';
import Rating from './screens/Rating';
import Login from './screens/Login';
import Register from './screens/Register';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path='/' element={<MainPage/>}/>
        <Route element={<PrivateRoute />}>
          <Route path='/multiplayer' element={<Multiplayer />} />
          
        </Route>
        <Route path='/rating' element={<Rating />}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/register' element={<Register/>}/>
        <Route path='*' element={<Navigate to='/register' />} />

        <Route path='/localgame' element={<LocalGame/>}/>
        <Route path='/sologame' element={<SoloGame/>}/>
      </Routes>
    </Router>
  </React.StrictMode>
);

