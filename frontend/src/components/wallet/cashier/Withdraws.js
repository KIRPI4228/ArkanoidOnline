import '../../TableStyle.css'
import {useTranslation} from "react-i18next";
import {useEffect, useState} from "react";
import {get} from "../../../Rest";

const Withdraws = () => {
    const {t} = useTranslation();
    const [withdraws, setWithdraws] = useState(new Array());

    async function initWithdraws() {
        const response = await get('/cashier/withdraw/withdraws').catch(error => console.log(error));

        if (response.data === undefined) {
            return;
        }

        setWithdraws(response.data);
    };

    useEffect(() => {
        initWithdraws();
    }, []);

    return (
        <div className="component-container table-container">
            <table>
                <thead>
                <tr className="table-first-row">
                    <td className="table-right-border">{t('withdraws.label.type')}</td>
                    <td className="table-right-border">{t('withdraws.label.status')}</td>
                    <td className="table-right-border">{t('withdraws.label.requisites')}</td>
                    <td className="table-left-border">{t('withdraws.label.amount')}</td>
                </tr>
                </thead>

                <tbody>
                {withdraws
                    .map(withdraw =>  (
                            <tr>
                                <td className="table-right-border">{withdraw.type}</td>
                                <td className="table-right-border">{withdraw.status}</td>
                                <td className="table-right-border">{withdraw.requisites}</td>
                                <td className="table-left-border">{withdraw.amount}</td>
                            </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Withdraws;