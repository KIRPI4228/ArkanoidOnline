import Header from "../components/header/Header";
import TransactionHistory from "../components/wallet/TransactionHistory";

const TransactionsHistoryPage = () => {
    return (
        <>
            <Header />
            <div className="centered-component-holder">
                <TransactionHistory />
            </div>
        </>
    );
}

export default TransactionsHistoryPage;