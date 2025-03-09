import Header from "../../components/header/Header";
import Deposit from "../../components/wallet/cashier/Deposit";

const DepositPage = () => {
    return (
        <>
            <Header/>
            <div className="centered-component-holder">
                <Deposit className="centered-component-holder" />
            </div>
        </>
    );
}

export default DepositPage;