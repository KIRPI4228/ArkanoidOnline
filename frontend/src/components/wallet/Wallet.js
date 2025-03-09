import '../ComponentContainerStyles.css';
import './WalletStyle.css'
import '../../InputsStyles.css'
import {useTranslation} from "react-i18next";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {get, post} from "../../Rest";

const Wallet = () => {
    const navigate = useNavigate();

    const [coins, setCoins] = useState(123);
    const [cash, setCash] = useState(41232);

    const {t} = useTranslation();

    function exchange() {
        post('/cashier/exchange')
            .then(response => {
                window.location.reload();
            })
            .catch(error => console.log(error))
    }

    async function initBalance() {
        const response = await get('/user/info').catch(error => console.log(error));

        if (response.data === undefined) {
            return;
        }

        setCash(response.data.wallet.cashBalance.balance);
        setCoins(response.data.wallet.coinBalance.balance);
    }

    useEffect(() => {
        initBalance();
    }, []);



    return (
        <div className="component-container wallet-container">
            <div className="wallet-header-container">
                <h2 className="wallet-text">{t('wallet.header')}</h2>
                <div className="component-inner-container wallet-balance-container">
                    <h4 className="wallet-text">coins {coins}</h4>
                    <div className="wallet-balance-separator"></div>
                    <h4 className="wallet-text">₽{cash}</h4>
                </div>
            </div>
            <div className="wallet-buttons-container">
                <input className="styled-button white-outline component-button" type="button" value={t('wallet.button.deposit')} onClick={() => navigate('/cashier/deposit')}></input>
                <input className="styled-button white-outline component-button" type="button" value={t('wallet.button.exchange')} onClick={() => exchange()}></input>
                <input className="styled-button gold component-button" id="wallet-withdraw-button" type="button" value={t('wallet.button.withdraw')} onClick={() => navigate('/cashier/withdraw')}></input>
                <input className="styled-button blue component-button" id="wallet-history-button" type="button" value={t('wallet.button.history')} onClick={() => navigate('/history')}></input>
                <input className="styled-button gold component-button" id="wallet-withdraws-button" type="button" value={t('wallet.button.withdraws')} onClick={() => navigate('/cashier/withdraws')}></input>
            </div>

        </div>
    );
}

export default Wallet;