import { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const OAuthRedirectHandler = () => {
    const location = useLocation();
    const { handleOAuthRedirect } = useAuth();

    useEffect(() => {
        const urlParams = new URLSearchParams(location.search);
        const token = urlParams.get('token');

        if (token) {
            handleOAuthRedirect(token);
        }
    }, [location, handleOAuthRedirect]);

    return (
        <div className="flex flex-col items-center justify-center min-h-screen">
            <div className="w-16 h-16 border-t-4 border-blue-500 border-solid rounded-full animate-spin mb-4"></div>
            <p className="text-lg text-gray-700">Processing your login...</p>
        </div>
    );
};

export default OAuthRedirectHandler;
