import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
    const { user, isAuthenticated, loading, logout } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (!loading && !isAuthenticated) {
            navigate('/');
        }
    }, [isAuthenticated, loading, navigate]);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div className="dashboard-container">
            <div className="dashboard-header">
                <h1>Welcome to Your Dashboard</h1>
                {user && (
                    <div className="user-info">
                        {user.picture && <img src={user.picture} alt="Profile" className="profile-picture" />}
                        <div className="user-details">
                            <h2>{user.name}</h2>
                            <p>{user.email}</p>
                        </div>
                    </div>
                )}
                <button onClick={logout} className="logout-button">Logout</button>
            </div>

            <div className="dashboard-content">
                {/* Your dashboard content here */}
                <p>This is your dashboard. You can start managing your skills and connections here.</p>
            </div>
        </div>
    );
};

export default Dashboard;
