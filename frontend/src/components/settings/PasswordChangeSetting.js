import {useTranslation} from "react-i18next";
import {useState} from "react";
import {post} from "../../Rest";

const PasswordChangeSetting = () => {
    const {t} = useTranslation();

    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [newPasswordRepeated, setNewPasswordRepeated] = useState('');
    const [error, setError] = useState('');



    function changePassword() {
        setError('');

        if (newPassword !== newPasswordRepeated) {
            setError('error.credentials.password.not_similar');
            return;
        }

        const parameter = {
            oldPassword: oldPassword,
            newPassword: newPassword
        }

        post('/user/change-password', parameter)
            .then(response => {
                alert("successfully changed password");
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                if (error.response.data !== undefined) {
                    setError(error.response.data);
                }
            });
    }



    return (
        <div className="component-container" style={{width: "30vw"}}>
            <h1 className="component-text-name">{t('change_password_setting.header')}</h1>
            <h2 className="component-text-name">{t('input.old_password')}</h2>
            <input type="password" className="styled-text-field component-text-field" onChange={(event) => setOldPassword(event.target.value)}></input>
            <div>
                <h2 className="component-text-name">{t('input.new_password')}</h2>
                <input type="password" className="styled-text-field component-text-field" onChange={(event) => setNewPassword(event.target.value)}></input>
                <h2 className="component-text-name">{t('input.repeat_new_password')}</h2>
                <input type="password" className="styled-text-field component-text-field" onChange={(event) => setNewPasswordRepeated(event.target.value)}></input>
            </div>
            <p className="styled-error-text">{t(error)}</p>
            <input type="button" value={t('change_password_setting.button.submit')} className="styled-button blue component-button" onClick={() => changePassword()}></input>
        </div>
    );
}

export default PasswordChangeSetting;