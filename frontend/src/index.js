import React, {Suspense} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './InputsStyles.css'
import reportWebVitals from './reportWebVitals';
import Router from "./components/Router";

import './i18n'
import i18n from "./i18n";

const root = ReactDOM.createRoot(document.getElementById('root'));

i18n.changeLanguage(navigator.language);

localStorage.setItem("ip", process.env.REACT_APP_GAME_IP);
localStorage.setItem("port", process.env.REACT_APP_GAME_PORT);
localStorage.setItem("secured", process.env.REACT_APP_GAME_SECURED);

root.render(
    <React.StrictMode>
        <Suspense fallback={<div><h1>Loading...</h1></div>}>
            <Router />
        </Suspense>
    </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
