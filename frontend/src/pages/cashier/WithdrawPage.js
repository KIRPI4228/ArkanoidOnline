import Header from "../../components/header/Header";
import Withdraw from "../../components/wallet/cashier/Withdraw";

const WithdrawPage = () => {
    return (
        <>
            <Header/>
            <div className="centered-component-holder">
                <Withdraw />
            </div>
        </>
    );
}

export default WithdrawPage;