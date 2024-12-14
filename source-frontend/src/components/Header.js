import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

const Header = () => {
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
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </>
    );
};

export default Header;