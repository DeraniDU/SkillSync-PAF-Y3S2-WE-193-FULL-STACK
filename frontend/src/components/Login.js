import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { GoogleLogin } from '@react-oauth/google';
import { jwtDecode } from "jwt-decode";
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

  const onGoogleLoginSuccess = (credentialResponse) => {
    console.log("Google login success:", credentialResponse);
    
    try {
      const decoded = jwtDecode(credentialResponse.credential);
      console.log("Decoded token:", decoded);
      
      // Store Google login info temporarily
      localStorage.setItem("googleToken", credentialResponse.credential);
      
      // Redirect to backend OAuth endpoint to process the token
      window.location.href = "http://localhost:8080/oauth2/authorization/google";
    } catch (error) {
      console.error("Error decoding Google token:", error);
      setMessage("Failed to process Google login. Please try again.");
    }
  };

  const onGoogleLoginError = () => {
    setMessage("Google login failed. Please try again.");
  };

  return (
    <div className="col-md-12">
      <div className="card card-container">
        <img
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          alt="profile-img"
          className="profile-img-card"
        />

        <form onSubmit={handleLogin}>
          <div className="form-group">
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

          <div className="form-group">
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

          <div className="form-group">
            <button
              className="btn btn-primary btn-block"
              disabled={loading}
            >
              {loading && (
                <span className="spinner-border spinner-border-sm"></span>
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

        <div className="mt-3 text-center">
          <p>OR</p>
          <GoogleLogin
            onSuccess={onGoogleLoginSuccess}
            onError={onGoogleLoginError}
          />

          {/* Alternative direct link to Google OAuth */}
          <div className="mt-3">
            <a 
              href="http://localhost:8080/oauth2/authorization/google"
              className="btn btn-danger btn-block"
            >
              Sign in with Google
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
