import Header from "../../components/header/Header";
import Withdraws from "../../components/wallet/cashier/Withdraws";

const WithdrawsPage = () => {
    return (
        <>
            <Header />
            <div className="centered-component-holder"><Withdraws /></div>
        </>
    );
}

export default WithdrawsPage;