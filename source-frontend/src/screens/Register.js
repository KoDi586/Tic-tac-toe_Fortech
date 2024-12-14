import { Button, Card, Container, Form } from "react-bootstrap";
import { Link } from 'react-router-dom';

import './Login.css'


const Register = () => {
    return (
        <Container style={{marginTop: '200px'}} className="d-flex flex-wrap justify-content-center">
            <Card style={{width: '700px'}}>
                <Form style={{margin: '75px', marginBottom: '25px'}}>
                    <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Регистрация</Form.Label>
                        <Form.Control type="email" placeholder="Логин" />
                    </Form.Group>
                    <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicPassword">
                        <Form.Control  style={{marginBottom: '15px'}} type="password" placeholder="Пароль" />
                        <Form.Text className="text-muted">
                            Придумайте логин и пароль для создания нового аккаунта.
                        </Form.Text>
                    </Form.Group>
                    <Form.Group style={{textAlign: 'center'}} className="mb-3" controlId="formBasicPassword">
                        <Button  variant="primary" type="submit">
                            Войти
                        </Button>
                    </Form.Group>
                </Form>
                <Card.Footer className="foot_reg foot_reg__hover" as={Link} to='/login' style={{textAlign: 'center'}}>
                    <p >Нажмите сюда если есть аккаунт.</p>
                </Card.Footer>
            </Card>
        </Container>
    )
}

export default Register;