import React, { useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import AuthService from "../services/auth.service";

const OAuth2RedirectHandler = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    // Get token and user info from URL params
    const params = new URLSearchParams(location.search);
    const token = params.get("token");
    const name = params.get("name");
    const email = params.get("email");
    const id = params.get("id");
    
    if (token) {
      console.log("Received OAuth2 redirect with token and user info:", { name, email });
      
      // Store user information with token
      const userData = {
        accessToken: token,
        username: name,
        email: email,
        id: id,
        tokenType: "Bearer"
      };
      
      localStorage.setItem("user", JSON.stringify(userData));
      navigate("/profile");
    } else {
      // Handle error
      navigate("/login", { 
        state: { 
          error: "OAuth2 authentication failed. Please try again." 
        }
      });
    }
  }, [location, navigate]);

  return (
    <div className="d-flex justify-content-center align-items-center" style={{height: "100vh"}}>
      <div className="spinner-border text-primary" role="status">
        <span className="sr-only">Loading...</span>
      </div>
    </div>
  );
};

export default OAuth2RedirectHandler;
