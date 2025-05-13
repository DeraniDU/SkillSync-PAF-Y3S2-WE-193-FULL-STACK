import React, { useState, useEffect } from "react";
import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import UserService from "../services/user.service";

const Profile = () => {
  const [currentUser, setCurrentUser] = useState(null);
  const [content, setContent] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const user = AuthService.getCurrentUser();
    console.log("Current user from localStorage:", user);
    
    if (user) {
      setCurrentUser(user);
      
      UserService.getUserContent().then(
        (response) => {
          setContent(response.data);
          setLoading(false);
        },
        (error) => {
          console.error("Error fetching user content:", error);
          setError(error.response?.data?.message || "Error loading protected content");
          setContent("Failed to load protected content. Please try logging in again.");
          setLoading(false);
        }
      );
    } else {
      setLoading(false);
    }
  }, []);

  if (loading) {
    return (
      <div className="d-flex justify-content-center my-5">
        <div className="spinner-border" role="status">
          <span className="sr-only">Loading...</span>
        </div>
      </div>
    );
  }

  if (!currentUser) {
    return <Navigate to="/login" />;
  }

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <strong>{currentUser.username || "User"}</strong> Profile
        </h3>
      </header>
      {currentUser.accessToken && (
        <p>
          <strong>Token:</strong>{" "}
          {currentUser.accessToken.substring(0, 20)}...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)}
        </p>
      )}
      <p>
        <strong>Id:</strong> {currentUser.id}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.email}
      </p>
      {currentUser.roles && (
        <>
          <strong>Authorities:</strong>
          <ul>
            {currentUser.roles.map((role, index) => (
              <li key={index}>{role}</li>
            ))}
          </ul>
        </>
      )}
      <div className="card">
        <div className="card-header">Protected Content</div>
        <div className="card-body">
          {error ? (
            <div className="alert alert-danger">{error}</div>
          ) : (
            content
          )}
        </div>
      </div>
    </div>
  );
};

export default Profile;
