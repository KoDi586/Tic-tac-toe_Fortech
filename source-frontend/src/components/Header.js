import { useState } from "react";
import { Button } from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from 'react-bootstrap/Navbar';
import { Link, useNavigate } from 'react-router-dom';
import AuthService from "../helper/AuthService";

const Header = () => {

    const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token')); // Проверяем наличие токена
    const navigate = useNavigate();


    const handleLogin = async () => {
        navigate('/login')
    };

    const handleLogout = () => {
        AuthService.logout();
        setIsLoggedIn(false);
        window.location.reload();
    };

    const name = localStorage.getItem('username');

    return (
        <>
            <Navbar bg="primary" data-bs-theme="dark" fixed="top">
                <Container>
                    <Navbar.Brand as={Link} to="/">Tic-Tac-Toee</Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            <Nav.Link as={Link} to="/">Главное меню</Nav.Link>
                            <Nav.Link as={Link} to="/sologame">Одиночная игра</Nav.Link>
                            <Nav.Link as={Link} to="/localgame">Локальная игра</Nav.Link>
                            <Nav.Link as={Link} to="/multiplayer">Мультиплеер</Nav.Link>
                            <Nav.Link as={Link} to="/rating">Рейтинг</Nav.Link>
                        </Nav>
                        
                        <Button 
                            variant={isLoggedIn ? "outline-light" : "light"} 
                            onClick={isLoggedIn ? handleLogout : handleLogin}
                        >
                            {isLoggedIn ? 'Выйти' : 'Войти'}
                        </Button>

                        <p style={{ color: 'white', marginLeft: '25px', marginTop: '15px'}}>{isLoggedIn ? "Пользователь: " + name  : ''}</p>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
};

export default Header;