import Header from "../components/header/Header";
import {post} from "../Rest";
import {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";

async function sendVerificationRequest(setIsVerified, key) {
    const response = await post("/user/auth/reset/verify?key=" + key).catch(error => console.log(error));

    if (response === undefined) {
        return;
    }

    setIsVerified(true);

    localStorage.setItem("token", response.data.token);
}


const ResetPasswordVerifyPage = () => {
    const [ isVerified, setIsVerified ] = useState(false);
    const {t} = useTranslation();
    const queryParameters = new URLSearchParams(window.location.search);
    const key = queryParameters.get('key');

    useEffect(() => {sendVerificationRequest(setIsVerified, key)}, [key]);

    return (
        <>
            <Header />
            <h1 style={{color: "white"}}>{isVerified ? t('reset_password_verified.text') : ""}</h1>
        </>
    );
}

export default ResetPasswordVerifyPage;