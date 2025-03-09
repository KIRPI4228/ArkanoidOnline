import '../ComponentContainerStyles.css';
import './AuthStyle.css'
import {useState} from "react";
import {post} from "../../Rest";
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";

const ResetPassword = () => {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [newPasswordRepeated, setNewPasswordRepeated] = useState('');
    const [error, setError] = useState('');
    const {t} = useTranslation();

    function resetPassword() {
        setError('');

        if (newPassword !== newPasswordRepeated) {
            setError('error.credentials.password.not_similar');
            return;
        }

        const parameters = {
            email: email,
            newPassword: newPassword
        }

        post('/user/auth/reset', parameters)
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
                <h2 className="component-text-name">{t('input.email')}</h2>
                <input type="text" className="styled-text-field component-text-field" onChange={(event) => setEmail(event.target.value)}></input>
            </div>

            <div className="auth-password-container">
                <h2 className="component-text-name">{t('input.new_password')}</h2>
                <input type="password" className="styled-text-field component-text-field" onChange={(event) => setNewPassword(event.target.value)}></input>
                <h2 className="component-text-name">{t('input.repeat_new_password')}</h2>
                <input type="password" className="styled-text-field component-text-field" onChange={(event) => setNewPasswordRepeated(event.target.value)}></input>
            </div>

            <p className="styled-error-text">{t(error)}</p>

            <input type="button" value={t('input.submit_reset_password')} className="styled-button blue component-button" onClick={() => resetPassword()}></input>
        </div>
    );
}

export default ResetPassword;