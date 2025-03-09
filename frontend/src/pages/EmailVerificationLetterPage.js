import Header from "../components/header/Header";
import {useTranslation} from "react-i18next";

const EmailVerificationLetterPage = () => {
    const {t} = useTranslation();

    return (
        <>
            <Header />
            <h1  style={{color: "white"}}>{t('email_verification_letter_sent.text')}</h1>
        </>
    );
}

export default EmailVerificationLetterPage