import { Button, Card, Container, Form } from "react-bootstrap";
import { Link, useNavigate } from 'react-router-dom';

import './Login.css'
import { useState } from "react";
import AuthService from "../helper/AuthService";


const Register = () => {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        AuthService.register(username, password)
            .then(data => {
                navigate('/');
            })
            .catch(error => {

            });
    };

    return (
        <>
            <Container onSubmit={handleSubmit} style={{marginTop: '200px'}} className="d-flex flex-wrap justify-content-center">
                <Card style={{width: '700px'}}>
                    <Form style={{margin: '75px', marginBottom: '25px'}}>
                        <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Регистрация</Form.Label>
                            <Form.Control type="text" placeholder="Логин" onChange={(e) => setUsername(e.target.value)}/>
                        </Form.Group>
                        <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicPassword">
                            <Form.Control  style={{marginBottom: '15px'}} type="password" placeholder="Пароль"  onChange={(e) => setPassword(e.target.value)}/>
                            <Form.Text className="text-muted">
                                Придумайте логин и пароль для создания нового аккаунта.
                            </Form.Text>
                        </Form.Group>
                        <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicPassword">
                            <Button  variant="primary" type="submit">
                                Зарегистрироваться
                            </Button>
                        </Form.Group>
                    </Form>
                    <Card.Footer className="foot_reg foot_reg__hover" as={Link} to='/login' style={{textAlign: 'center'}}>
                        <p >Нажмите сюда если есть аккаунт.</p>
                    </Card.Footer>
                </Card>
            </Container>
            <Button variant="light" style={{position: 'fixed', right:'25px', bottom: '25px'}} as={Link} to='/'>
                Вернутся на главную страницу
            </Button>
        </>
        
    )
}

export default Register;