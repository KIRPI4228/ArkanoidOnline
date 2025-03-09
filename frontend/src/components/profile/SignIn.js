import '../ComponentContainerStyles.css';
import './AuthStyle.css'
import {useState} from "react";
import {post} from "../../Rest";
import {Link, useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";




const SignIn = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const {t} = useTranslation();

    function signIn() {
        setError('');

        const parameters = {
            email: email,
            password: password
        }

        post('/user/auth/sign-in', parameters)
            .then(response => {
                localStorage.setItem("token", response.data.token);
                navigate('/');
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                setError('error.credentials.incorrect_username_or_password');
            });
    }

    return (
        <div className="component-container">
            <div>
                <h2 className="component-text-name">{t('input.email')}</h2>
                <input type="text" className="styled-text-field component-text-field" onChange={(event) => setEmail(event.target.value)}></input>
            </div>

            <div className="auth-password-container">
                <div className="auth-password-header-container">
                    <h2 className="component-text-name">{t('input.password')}</h2>
                    <Link className="component-link" to="/reset-password"><h5>{t('input.link_reset_password')}</h5></Link>
                </div>

                <input type="password" className="styled-text-field component-text-field" onChange={(event) => setPassword(event.target.value)}></input>
            </div>

            <p className="styled-error-text">{t(error)}</p>

            <input type="button" value={t('input.submit_sign_in')} className="styled-button blue component-button" onClick={() => signIn()}></input>
        </div>
    );
}

export default SignIn;