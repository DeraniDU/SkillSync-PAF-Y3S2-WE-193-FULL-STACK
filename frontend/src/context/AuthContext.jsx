import React, { createContext, useContext, useState, useEffect } from 'react';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Check if user is already logged in
        const token = localStorage.getItem('token');
        if (token) {
            setIsAuthenticated(true);
            fetchUserInfo(token);
        } else {
            // Check for token in URL (OAuth2 redirect)
            const urlParams = new URLSearchParams(window.location.search);
            const tokenParam = urlParams.get('token');

            if (tokenParam) {
                localStorage.setItem('token', tokenParam);
                setIsAuthenticated(true);
                fetchUserInfo(tokenParam);
                // Clean up URL
                window.history.replaceState({}, document.title, window.location.pathname);
            } else {
                setLoading(false);
            }
        }
    }, []);

    const fetchUserInfo = async (token) => {
        try {
            const response = await fetch('http://localhost:8080/api/users/me', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                const userData = await response.json();
                setUser(userData);
            } else {
                logout();
            }
        } catch (error) {
            console.error('Error fetching user data:', error);
            logout();
        } finally {
            setLoading(false);
        }
    };

    const login = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorize/google';
    };

    const logout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, user, loading, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
