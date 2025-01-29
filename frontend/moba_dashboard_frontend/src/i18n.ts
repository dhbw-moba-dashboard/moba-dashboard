//import libraries
import i18n from "i18next";
import {initReactI18next} from "react-i18next";
import HttpBackend from "i18next-http-backend";

//initialize i18n translator
i18n
    .use(HttpBackend)
    .use(initReactI18next)
    .init({
        backend: {
            loadPath: "/data/languages/{{lng}}.json",
        },
        lng: 'en',
        fallbackLng: 'en',
        interpolation: {
            escapeValue: false
        },
        debug: true
    });

//export i18n as default
export default i18n;
