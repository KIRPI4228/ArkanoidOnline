import '../ComponentContainerStyles.css';
import './AuthStyle.css'
import {useState} from "react";
import {post} from "../../Rest";
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";

const SignUp = () => {
    const navigate = useNavigate();

    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [repeatedPassword, setRepeatedPassword] = useState('');
    const [error, setError] = useState('');
    const {t} = useTranslation();

    const queryParameters = new URLSearchParams(window.location.search);
    const referral = queryParameters.get('referral');

    function signUp() {
        setError('');

        if (password !== repeatedPassword) {
            setError('error.credentials.password.not_similar');
            return;
        }

        const parameters = {
            username: username,
            email: email,
            password: password,
            referral: referral
        }

        post('/user/auth/sign-up', parameters)
            .then(response => {
                navigate('/verification-letter-page');
                localStorage.setItem("token", response.data.token);
            })
            .catch(error => {
                console.log(error);
                if (error.response.data !== undefined) {
                    setError(error.response.data);
                }
            });


    }

    return (
        <div className="component-container">
            <div>
                <h2 className="component-text-name">{t('input.username')}</h2>
                <input type="text" className="styled-text-field component-text-field" onChange={(event) => setUsername(event.target.value)}></input>
            </div>
            <div>
                <h2 className="component-text-name">{t('input.email')}</h2>
                <input type="text" className="styled-text-field component-text-field" onChange={(event) => setEmail(event.target.value)}></input>
            </div>

            <div className="auth-password-container">
                <div>
                    <h2 className="component-text-name">{t('input.password')}</h2>
                    <input type="password" className="styled-text-field component-text-field" onChange={(event) => setPassword(event.target.value)}></input>
                </div>
                <div>
                    <h2 className="component-text-name">{t('input.repeat_password')}</h2>
                    <input type="password" className="styled-text-field component-text-field" onChange={(event) => setRepeatedPassword(event.target.value)}></input>
                </div>
            </div>

            <p className="styled-error-text">{t(error)}</p>

            <input type="button" className="styled-button blue component-button" value={t('input.submit_sign_up')} onClick={() => signUp()}></input>
        </div>
    );
}

export default SignUp;