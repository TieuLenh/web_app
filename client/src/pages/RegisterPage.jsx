import RegisterForm from "../forms/register"

import '../styles/auth.css'
import '../styles/registerPage.css'

const RegisterPage = () => {

    return (

        <div className="auth-container register-page">

            <div
                className="
                    auth-box
                    shadow-lg
                "
            >

                <div className="form-side">

                    <div className="form-wrapper">

                        <RegisterForm />

                    </div>

                </div>

                <div className="banner-side">

                    <div>

                        <h1 className="fw-bold mb-3">
                            Create Account
                        </h1>

                        <p>
                            Join and start your journey with us
                        </p>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default RegisterPage;