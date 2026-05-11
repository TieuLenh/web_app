import { useState } from "react";

import { useNavigate } from "react-router-dom";
import { isSignedIn } from "../../logics/localstorage";
import { getUsername, clearAuthData, getRole } from "../../logics/localstorage";

import "../styles/account.css";

const Account = () => {
    const navigate = useNavigate();
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(isSignedIn());
    return (
        <div className="account-container">
            <button
                className="avatar-btn"
                onClick={() =>
                    setIsMenuOpen(!isMenuOpen)
                }
            >
                <img
                    src="https://cdn-icons-png.flaticon.com/512/1077/1077114.png"
                    alt="account"
                    className="avatar"
                />
            </button>
            {isMenuOpen && (
                <div className="account-menu">
                    {isLoggedIn ? (
                        <>
                            <p>
                                {getRole() === 'ADMIN' ? 'Admin' : 'User'}: {getUsername()}
                            </p>
                            <button
                                onClick={() => navigate("/profile")}
                            >
                                Profile
                            </button>
                            <button
                                onClick={() => {
                                    clearAuthData();
                                    setIsLoggedIn(false);
                                    navigate("/");
                                }}
                            >
                                Logout
                            </button>
                        </>
                    ) : (
                        <>
                            <button
                                onClick={() => navigate("/login")}
                            >
                                Login
                            </button>
                        </>
                    )}
                </div>
            )}
        </div>
    );
}

export default Account;