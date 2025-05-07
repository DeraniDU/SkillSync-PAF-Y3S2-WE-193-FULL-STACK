// src/pages/HomePage.jsx
import { useAuth } from '../context/AuthContext';
import googleLogo from '../assets/google-logo.svg';
import '../components/Navbar.jsx'


const HomePage = () => {
    const { login, isAuthenticated } = useAuth();

    return (
        <div className="home-container">
            <div className="home-content">
                <h1>Welcome to SkillSync</h1>
                <p>Connect and collaborate with other skilled professionals</p>

                {!isAuthenticated ? (
                    <button onClick={login} className="google-login-button">
                        <img src={googleLogo} alt="Google" />
                        <span>Sign in with Google</span>
                    </button>
                ) : (
                    <div className="already-logged-in">
                        <p>You are already logged in!</p>
                        <a href="/dashboard" className="dashboard-link">
                            Go to Dashboard
                        </a>
                    </div>
                )}
            </div>
        </div>
    );
};

export default HomePage;
