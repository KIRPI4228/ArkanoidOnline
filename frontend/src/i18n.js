import i18n from "i18next";
import I18NextHttpBackend from "i18next-http-backend";
import detector from "i18next-browser-languagedetector";
import {initReactI18next} from "react-i18next";

i18n.use(I18NextHttpBackend)
    .use(detector)
    .use(initReactI18next)
    .init({
        fallbackLng: "en",
        debug: true,
        detection: {
            order: ['queryString', 'cookie'],
            cache: ['cookie']
        },
        interpolation: {
            escapeValue: false
        }
    });

export default i18n;