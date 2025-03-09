import Header from "../components/header/Header";
import PasswordChangeSetting from "../components/settings/PasswordChangeSetting";
import UsernameChangeSetting from "../components/settings/UsernameChangeSetting";

const SettingsPage = () => {
    return (
        <>
            <Header />
            <PasswordChangeSetting />
            <br/>
            <br/>
            <UsernameChangeSetting />
        </>
    );
}

export default SettingsPage;