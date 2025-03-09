import {useNavigate} from "react-router-dom";
import './HeaderProfileSector.css'
import {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";
import {isUserAuthorized} from "../../UserApi";

const HeaderProfileSector = () => {
    const [profileSector, setSector] = useState();
    const {t} = useTranslation();
    const navigate = useNavigate();

    const SignedInProfile = () => {
        return(
            <div className="header-profile-sector" id="header-logged-in-profile-sector">
                <input type="button" className="styled-button" value={t('header.sign_out')} onClick={() => signOut(navigate)}></input>
                <input type="button" className="styled-button" value={t('header.settings')} onClick={() => navigate('/settings')}></input>
            </div>
        );
    };

    const NotSignedInProfile = () => {
        return (
            <div className="header-profile-sector" id="header-not-logged-in-profile-sector">
                <input type="button" className="styled-button blue" onClick={() => navigate("/sign-in")} value={t('header.sign_in')}></input>
                <input type="button" className="styled-button" onClick={() => navigate("/sign-up")} value={t('header.sign_up')}></input>
            </div>
        );
    };

    async function setProfileSector() {
        const isAuthorized = await isUserAuthorized();

        if (isAuthorized === false) {
            setSector(NotSignedInProfile());
        } else if (isAuthorized === true) {
            setSector(SignedInProfile());
        }
    }

    function signOut(navigate) {
        localStorage.setItem("token", null);
        navigate('/');
        window.location.reload();
    }

    useEffect(() => {
        setProfileSector();
    }, [] );

    return profileSector;
}

export default HeaderProfileSector;