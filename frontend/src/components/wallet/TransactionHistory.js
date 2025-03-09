import '../ComponentContainerStyles.css'
import '../TableStyle.css'
import {useTranslation} from "react-i18next";
import {useEffect, useState} from "react";
import {get} from "../../Rest";

const TransactionHistory = () => {
    const {t} = useTranslation();
    const [transactions, setTransactions] = useState(new Array());

    async function initTransactions() {
        const response = await get('/user/info').catch(error => console.log(error));

        if (response.data === undefined) {
            return;
        }

        setTransactions(response.data.wallet.cashBalance.history.allTransactions);
    };

    useEffect(() => {
        initTransactions();
    }, []);

    return (
        <div className="component-container table-container">
            <table>
                <thead>
                <tr className="table-first-row">
                    <td className="table-right-border">{t('history.label.date')}</td>
                    <td className="table-right-border">{t('history.label.description')}</td>
                    <td className="table-right-border">{t('history.label.amount')}</td>
                    <td className="table-left-border">{t('history.label.balance')}</td>
                </tr>
                </thead>
                <tbody>

                {transactions
                    .map(transaction => {
                        let amountColor = "red";
                        if (transaction.amount >= 0) {
                            amountColor = "green";
                        }

                        return (
                            <tr>
                                <td className="table-right-border">{new Date(transaction.date).toLocaleString()}</td>
                                <td className="table-right-border">{transaction.description}</td>
                                <td className="table-right-border" style={{
                                    color: amountColor
                                }}>{transaction.amount}</td>
                                <td className="table-left-border">{transaction.balance}</td>
                            </tr>
                        );
                    })}

                </tbody>
            </table>
        </div>
    );
}

export default TransactionHistory;