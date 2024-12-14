import { useState } from "react";
import Header from "../components/Header"
import { Container, Table } from "react-bootstrap";


const Rating = () => {

    // const [ rating, setRating ] = useState([])
    const rating = [
        {
            user_id: 1,
            user_name: 'Вася пупкин',
            rank: 1000
        },
        {
            user_id: 2,
            user_name: 'Вася пупкин',
            rank: 1000
        },
        {
            user_id: 3,
            user_name: 'Вася пупкин',
            rank: 1000
        },
    ]

    const highlightedUserId = 2; // Замените на нужный ID

    return (
        <>
            <Header/>
            <Container style={{marginTop: '75px', textAlign:'center'}}>
                <Table>
                    <thead>
                        <tr>
                            <th style={{backgroundColor: '#0d6efd', color:'white'}}>Место</th>
                            <th  style={{backgroundColor: '#0d6efd', color:'white'}}>Никнейм</th>
                            <th  style={{backgroundColor: '#0d6efd', color:'white'}}>Рейтинг</th>
                        </tr>
                    </thead>
                    {rating.map((user, key) => (
                        <tbody key={user.user_id} >
                            <tr>
                                <td style={user.user_id === highlightedUserId ? { backgroundColor: '#79AFFE' } : {}}>{key + 1}</td>
                                <td style={user.user_id === highlightedUserId ? { backgroundColor: '#79AFFE' } : {}}>{user.user_name}</td>
                                <td style={user.user_id === highlightedUserId ? { backgroundColor: '#79AFFE' } : {}}>{user.rank}</td>
                            </tr>
                        </tbody>
                    ))}
                </Table>
                
                
            </Container>
        </>
    )
}

export default Rating;