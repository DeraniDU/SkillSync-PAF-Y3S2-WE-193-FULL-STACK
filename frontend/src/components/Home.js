import React, { useState, useEffect } from "react";
import UserService from "../services/user.service";

const Home = () => {
  const [content, setContent] = useState("");

  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        // Fix: Handle the error object properly
        let errorMessage;
        if (error.response && error.response.data) {
          if (typeof error.response.data === 'object') {
            // Convert object to a string representation
            errorMessage = JSON.stringify(error.response.data);
          } else {
            errorMessage = String(error.response.data);
          }
        } else {
          errorMessage = error.message || "Unknown error occurred";
        }
        
        setContent(errorMessage);
      }
    );
  }, []);

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>{content}</h3>
        <div className="mt-4">
          <p>Welcome to our authentication demo application!</p>
          <p>This page is publicly accessible to everyone.</p>
          <p>Login or register to access protected content.</p>
        </div>
      </header>
    </div>
  );
};

export default Home;
