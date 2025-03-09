import {BrowserRouter, Route, Routes} from "react-router-dom";
import MainPage from "../pages/MainPage";
import SignUpPage from "../pages/SignUpPage";
import LoginPage from "../pages/SignInPage";
import EmailVerifyPage from "../pages/EmailVerifyPage";
import EmailVerificationLetterPage from "../pages/EmailVerificationLetterPage";
import ResetPasswordPage from "../pages/ResetPasswordPage";
import React from 'react';
import {useEffect, useState} from "react";
import {isUserAuthorized} from "../UserApi";
import DepositPage from "../pages/cashier/DepositPage";
import WithdrawPage from "../pages/cashier/WithdrawPage";
import TransactionsHistoryPage from "../pages/TransactionsHistoryPage";
import WithdrawsPage from "../pages/cashier/WithdrawsPage";
import SettingsPage from "../pages/SettingsPage";
import ResetPasswordVerifyPage from "../pages/ResetPasswordVerifyPage";
import MainNotAuthorizedPage from "../pages/MainNotAuthorizedPage";
import PageNotFoundPage from "../pages/PageNotFoundPage";
import GamePage from "../pages/GamePage";


const Router = () => {
    const [routes, setRoutes] = useState();

    const NotSignedInRoutes = () => {
        // TODO: create main page for not signed in users
        return new Array(
                <Route path="/" element={<MainNotAuthorizedPage />} />,
                <Route path="sign-up" element={<SignUpPage />} />,
                <Route path="sign-in" element={<LoginPage />} />,
                <Route path="reset-password" element={<ResetPasswordPage />} />,
                <Route path="sign-up/verify" element={<EmailVerifyPage />} />,
                <Route path="reset-password/verify" element={<ResetPasswordVerifyPage />} />
        );
    }

    const SignedInRoutes = () => {
        return new Array(
                <Route path="/" element={<MainPage />} />,
                <Route path="cashier/deposit" element={<DepositPage />} />,
                <Route path="cashier/withdraw" element={<WithdrawPage />} />,
                <Route path="cashier/withdraws" element={<WithdrawsPage />} />,
                <Route path="history" element={<TransactionsHistoryPage />} />,
                <Route path="settings" element={<SettingsPage />} />

        );
    }

    async function setExternalRoutes() {
        const isAuthorized = await isUserAuthorized();

        if (isAuthorized === false) {
            setRoutes(NotSignedInRoutes());
        } else if (isAuthorized === true) {
            setRoutes(SignedInRoutes());
        }
    }

    useEffect(() => {
        setExternalRoutes();
    }, []);


    return (
        <div className="app-container">
            <BrowserRouter>
                <Routes>
                    <Route path="verification-letter-page" element={<EmailVerificationLetterPage />} />
                    <Route path="game" element={<GamePage />}/>
                    <Route path="*" element={<PageNotFoundPage />} />

                    {routes}
                </Routes>

            </BrowserRouter>
        </div>
    );
}

export default Router;

