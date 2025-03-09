import Header from "../components/header/Header";
import {post} from "../Rest";
import {useEffect, useState} from "react";
import {useTranslation} from "react-i18next";

async function sendVerificationRequest(setIsVerified, key) {
    const response = await post("/user/auth/sign-up/verify?key=" + key).catch(error => console.log(error));

    if (response === undefined) {
        return;
    }

    setIsVerified(true);

    localStorage.setItem("token", response.data.token);
}


const EmailVerifyPage = () => {
    const [ isVerified, setIsVerified ] = useState(false);
    const {t} = useTranslation();
    const queryParameters = new URLSearchParams(window.location.search);
    const key = queryParameters.get('key');

    useEffect(() => {sendVerificationRequest(setIsVerified, key)}, [key]);

    return (
        <>
            <Header />
            <h1 style={{color: "white"}}>{isVerified ? t('email_verified.text') : ""}</h1>
        </>
    );
}

export default EmailVerifyPage;