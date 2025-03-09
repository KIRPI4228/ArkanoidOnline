import '../../ComponentContainerStyles.css'
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {useState} from "react";
import {post} from "../../../Rest";
import {parseTranslation} from "../../../ParseUtils";

const Withdraw = () => {
    const navigate = useNavigate();

    const [amount, setAmount] = useState(0);
    const [address, setAddress] = useState(0);
    const [error, setError] = useState('');

    const {t} = useTranslation();

    function withdraw() {
        const parameters = {
            amount: amount,
            requisites: address,
            type: "CRYPTO_WALLET"
        }

        post('/cashier/withdraw', parameters)
            .then(response => {
                navigate('/');
            })
            .catch(error => {
                console.log(error)
                if (error.response.data !== undefined) {
                    setError(parseTranslation(error.response.data, t));
                }
            })
    }


    return (
        <div className="component-container">
            <h1 className="component-text-name">{t('withdraw.header')}</h1>
            <div>
                <h2 className="component-text-name">{t('input.amount')}</h2>
                <input className="styled-text-field component-text-field" type="text" onChange={(event) => setAmount(event.target.value)}></input>
            </div>
            <div>
                <h2 className="component-text-name">{t('input.wallet-address')}</h2>
                <input className="styled-text-field component-text-field" type="text" onChange={(event) => setAddress(event.target.value)}></input>
            </div>

            <p className="styled-error-text">{t(error)}</p>

            <input className="styled-button blue component-button" type="button" value={t('withdraw.button.submit')} onClick={() => withdraw()}></input>
        </div>
    );
}

export default Withdraw;