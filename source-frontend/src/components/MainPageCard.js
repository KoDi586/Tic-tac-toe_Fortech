import { Card } from "react-bootstrap";
import { Link } from 'react-router-dom';

import './MainPageCard.css'

const MainPageCard = (props) => {
    return (
        <Card as={Link} to={props.href} className="card card__hover">
            <Card.Img variant="top" src={props.image} style={{width: "427px", height: "320px"}}/>
                <Card.Body>
                    <Card.Title style={{textAlign: 'center'}}>{props.title}</Card.Title>
                </Card.Body>
        </Card>
    );
};

export default MainPageCard;