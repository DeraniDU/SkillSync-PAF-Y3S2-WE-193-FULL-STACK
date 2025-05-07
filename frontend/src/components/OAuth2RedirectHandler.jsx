import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const OAuth2RedirectHandler = () => {
    const navigate = useNavigate();
    const { isAuthenticated, loading } = useAuth();

    useEffect(() => {
        if (!loading) {
            if (isAuthenticated) {
                navigate('/dashboard');
            } else {
                navigate('/');
            }
        }
    }, [isAuthenticated, loading, navigate]);

    return (
        <div className="oauth-loading-container">
            <p>Processing your authentication, please wait...</p>
        </div>
    );
};

export default OAuth2RedirectHandler;
