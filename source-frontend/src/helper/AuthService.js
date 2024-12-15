import axios from 'axios';
import ApiConfig from '../ApiConfig';
import { useState } from 'react';



const login = (username, password) => {
    return axios.post(ApiConfig.apiHTTP + ApiConfig.apiAuthLogin, { username, password })
        .then(response => {
            if (response.data) {
                localStorage.setItem('token', JSON.stringify(response.data.token))
                localStorage.setItem('user_id', JSON.stringify(response.data.user_id));
                localStorage.setItem('username', response.data.username);
            }
            return response.data;
        });
};

const register = (username, password) => {
    return axios.post(ApiConfig.apiHTTP + ApiConfig.apiAuthRegister, { username, password }).then(response => {
        if (response.data) {
            localStorage.setItem('token', JSON.stringify(response.data.token))
            localStorage.setItem('user_id', JSON.stringify(response.data.user_id));
            localStorage.setItem('username', response.data.username);
        }
        return response.data;
    });;
};

const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user_id');
    localStorage.removeItem('username');
};

export default {
    login, 
    register,
    logout
};