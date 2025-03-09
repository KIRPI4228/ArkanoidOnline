import Header from "../components/header/Header";
import {isUserAuthorized} from "../UserApi";

const MainNotAuthorizedPage = () => {
    isUserAuthorized().then(isAuthorized => {
        if (isAuthorized) {
            window.location.reload();
        }
    })

    return (
        <>
            <Header/>
        </>
    );
}

export default MainNotAuthorizedPage;