import Header from "../components/header/Header";
import SignIn from "../components/profile/SignIn";

const LoginPage = () => {
    return (
        <>
            <Header />
            <div className="centered-component-holder"><SignIn /></div>
        </>
    );
}

export default LoginPage;