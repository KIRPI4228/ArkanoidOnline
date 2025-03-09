import {useTranslation} from "react-i18next";
import {useState} from "react";
import {post} from "../../Rest";

const UsernameChangeSetting = () => {
    const {t} = useTranslation();

    const [newUsername, setNewUsername] = useState('');
    const [error, setError] = useState('');

    function changeUsername() {
        setError('');

        post('/user/change-username?newUsername=' + newUsername)
            .then(response => {
                alert("successfully changed username on ");
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
            <h1 className="component-text-name">{t('change_username_setting.header')}</h1>
            <div>
                <h2 className="component-text-name">{t('input.new_username')}</h2>
                <input type="text" className="styled-text-field component-text-field" onChange={(event) => setNewUsername(event.target.value)}></input>
            </div>
            <p className="styled-error-text">{t(error)}</p>
            <input type="button" value={t('change_username_setting.button.submit')} className="styled-button blue component-button" onClick={() => changeUsername()}></input>
        </div>
    );
}

export default UsernameChangeSetting;