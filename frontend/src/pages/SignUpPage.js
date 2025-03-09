import Header from "../components/header/Header";
import SignUp from "../components/profile/SignUp";

const SignUpPage = () => {
    return (
        <>
            <Header />
            <div className="centered-component-holder"><SignUp /></div>
        </>
    );
}

export default SignUpPage;