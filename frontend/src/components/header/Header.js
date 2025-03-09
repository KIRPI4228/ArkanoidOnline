import './Header.css';
import HeaderProfileSector from "./HeaderProfileSector";
import {Link} from "react-router-dom";
import { useTranslation } from "react-i18next";

const Header = () => {
    const {t} = useTranslation();

    return (
        <div className="header">
            <Link id="site-header-name" to="/"><h3>{t('header.name')}</h3></Link>
            <HeaderProfileSector />
        </div>
    );
}

export default Header;