import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { GoogleOAuthProvider } from '@react-oauth/google';
import { ThemeProvider } from './contexts/ThemeContext';

import NavBar from "./components/NavBar";
import Home from "./components/Home";
import Login from "./components/Login";
import Register from "./components/Register";
import Profile from "./components/Profile";
import ProtectedRoute from "./components/ProtectedRoute";
import OAuth2RedirectHandler from "./components/OAuth2RedirectHandler";
import Sidebar from "./components/Sidebar"; // Import the Sidebar component

const App = () => {
  return (
      <GoogleOAuthProvider clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID}>
        <ThemeProvider>
          <Router>
            <div className="flex">
              <Sidebar />
              <div className="flex-1 ml-0 md:ml-64">
                <NavBar />
                <div className="container mt-3 p-4">
                  <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/profile" element={
                      <ProtectedRoute>
                        <Profile />
                      </ProtectedRoute>
                    } />
                    <Route path="/oauth2/redirect" element={<OAuth2RedirectHandler />} />
                    {/* Add new routes for sidebar options */}
                    <Route path="/find-contributor" element={
                      <ProtectedRoute>
                        <div>Find Contributor Page</div>
                      </ProtectedRoute>
                    } />
                    <Route path="/ask-contribute" element={
                      <ProtectedRoute>
                        <div>Ask to Contribute Page</div>
                      </ProtectedRoute>
                    } />
                    <Route path="/add-post" element={
                      <ProtectedRoute>
                        <div>Add Post Page</div>
                      </ProtectedRoute>
                    } />
                    <Route path="/saved-posts" element={
                      <ProtectedRoute>
                        <div>Saved Posts Page</div>
                      </ProtectedRoute>
                    } />
                  </Routes>
                </div>
              </div>
            </div>
          </Router>
        </ThemeProvider>
      </GoogleOAuthProvider>
  );
};

export default App;
