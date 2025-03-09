import {useTranslation} from "react-i18next";
import '../../ComponentContainerStyles.css';
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {post} from "../../../Rest";

function deposit(amount, setError, navigate) {
    setError('');
    post('/cashier/deposit?amount=' + amount)
        .then(response => {
            navigate('/');
            window.location.reload();
        })
        .catch(error => {
            console.log(error);
            if (error.response.data !== undefined) {
                setError(error.response.data);
            }
        });
}


const Deposit = () => {
    const navigate = useNavigate();

    const [amount, setAmount] = useState(0);
    const [error, setError] = useState('');

    const {t} = useTranslation();

    return (
        <div className="component-container">
            <h1 className="component-text-name">{t('deposit.header')}</h1>
            <div>
                <h2 className="component-text-name">{t('input.amount')}</h2>
                <input className="styled-text-field component-text-field" type="text" onChange={(event) => setAmount(event.target.value)}></input>
            </div>
            <p className="styled-error-text">{t(error)}</p>

            <input className="styled-button blue component-button" type="button" value={t('deposit.button.submit')} onClick={() => deposit(amount, setError, navigate)}></input>
        </div>
    );
}

export default Deposit;