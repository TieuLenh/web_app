import SearchBar from "./searchbar";
import Account from "./account";

import "../styles/header.css";

const Header = ({ toggleSidebar }) => {

    return (

        <header className="header">

            {/* LEFT */}

            <div className="header-left">

                <button
                    className="menu-btn"
                    onClick={toggleSidebar}
                >
                    ☰
                </button>

                <a
                    href="/"
                    className="logo"
                >
                    AWEB
                </a>

            </div>

            {/* CENTER */}

            <div className="header-center">

                <SearchBar />

            </div>

            {/* RIGHT */}

            <div className="header-right">

                <Account />

            </div>

        </header>
    );
}

export default Header;