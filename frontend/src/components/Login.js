import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import AuthService from "../services/auth.service";

const Login = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    useEffect(() => {
        // If user is already logged in, redirect to profile
        if (AuthService.getCurrentUser()) {
            navigate("/profile");
        }
    }, [navigate]);

    const onChangeUsername = (e) => {
        setUsername(e.target.value);
    };

    const onChangePassword = (e) => {
        setPassword(e.target.value);
    };

    const handleLogin = (e) => {
        e.preventDefault();

        setMessage("");
        setLoading(true);

        AuthService.login(username, password)
            .then(() => {
                // Dispatch custom event to notify auth change
                window.dispatchEvent(new Event("auth-change"));
                navigate("/profile");
            })
            .catch((error) => {
                const resMessage =
                    (error.response &&
                        error.response.data &&
                        error.response.data.message) ||
                    error.message ||
                    error.toString();

                setLoading(false);
                setMessage(resMessage);
            });
    };

    return (
        <div className="col-md-12">
            <div className="card card-container p-5">
                <img
                    src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
                    alt="profile-img"
                    className="profile-img-card mb-3"
                />

                <h2 className="text-center mb-2">Welcome to SkillSync</h2>
                <p className="text-center text-muted mb-4">Connect, learn, and grow with professionals across industries</p>

                <form onSubmit={handleLogin} className="mb-4">
                    <div className="form-group mb-3">
                        <label htmlFor="username">Username</label>
                        <input
                            type="text"
                            className="form-control"
                            name="username"
                            value={username}
                            onChange={onChangeUsername}
                            required
                        />
                    </div>

                    <div className="form-group mb-3">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            name="password"
                            value={password}
                            onChange={onChangePassword}
                            required
                        />
                    </div>

                    <div className="form-group mb-3">
                        <button
                            className="btn btn-primary btn-block w-full"
                            disabled={loading}
                        >
                            {loading && (
                                <span className="spinner-border spinner-border-sm me-2"></span>
                            )}
                            <span>Login</span>
                        </button>
                    </div>

                    {message && (
                        <div className="form-group">
                            <div className="alert alert-danger" role="alert">
                                {message}
                            </div>
                        </div>
                    )}
                </form>

                <div className="text-center">
                    <p className="mb-2">OR</p>
                    <a
                        href="http://localhost:8080/oauth2/authorization/google"
                        className="btn w-full py-2 shadow-md transition-all duration-200 ease-in-out"
                        style={{
                            backgroundColor: "#dd4b39",
                            color: "white",
                            fontWeight: "bold",
                            borderRadius: "4px",
                            display: "flex",
                            alignItems: "center",
                            justifyContent: "center",
                            gap: "10px"
                        }}
                        onMouseOver={(e) => e.currentTarget.style.backgroundColor = "#c23321"}
                        onMouseOut={(e) => e.currentTarget.style.backgroundColor = "#dd4b39"}
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 48 48">
                            <path fill="#fff" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"/>
                            <path fill="#fff" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"/>
                            <path fill="#fff" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"/>
                            <path fill="#fff" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"/>
                        </svg>
                        Sign in with Google
                    </a>
                </div>

                <div className="text-center mt-4">
                    <p className="mb-0">
                        Don't have an account? <Link to="/register" className="text-primary">Sign up</Link>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Login;
