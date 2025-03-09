import Header from "../components/header/Header";
import Wallet from "../components/wallet/Wallet";
import {useTranslation} from "react-i18next";
import {useNavigate} from "react-router-dom";

const MainPage = () => {
    const navigate = useNavigate();
    const {t} = useTranslation();

    return (
        <>
            <Header />
            <h1 className="play-info-text">{t('play.info')}</h1>
            <input className="styled-button blue component-button" type="button" value={t('play.button')} onClick={() => navigate('/game')}></input>
            <br />
            <br />
            <br />
            <Wallet />
        </>
    );
}

export default MainPage;