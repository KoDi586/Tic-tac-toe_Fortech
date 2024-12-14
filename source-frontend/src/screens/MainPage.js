import { Container } from 'react-bootstrap';
import Header from '../components/Header';
import MainPageCard from '../components/MainPageCard';

const MainPage = () => {
    return (
        <>
            <Header/>
            <Container className="d-flex flex-wrap justify-content-center" style={{marginTop: "75px", gap: '30px'}}>
                <MainPageCard 
                    href='/sologame'
                    image='https://avatars.mds.yandex.net/i?id=7ca2bdf379c3338a505b2d1e9c1db6f505c15c54-10209290-images-thumbs&n=13'
                    title='Одиночная игра'
                /> 
                <MainPageCard 
                    href='/localgame'
                    image='https://avatars.mds.yandex.net/i?id=e8e062120bc5f176cd836c29513b9a10450d4dca-5026122-images-thumbs&n=13'
                    title='Локальная игра'
                /> 
                <MainPageCard 
                    href='/'
                    image='https://avatars.mds.yandex.net/i?id=08f2b761c43390fdd39d7033a1afa63bb8d092de-12927189-images-thumbs&n=13'
                    title='Многопользовательская игра'
                /> 
                <MainPageCard 
                    href='/'
                    image='https://www.nifi.ru/images/FILES/NEWS/2023/htqnbyub555.jpg'
                    title='Рейтинг'
                /> 
            </Container>
        </>
    );
};

export default MainPage;