// src/components/Navbar.jsx
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
    const { user, isAuthenticated, logout } = useAuth();
    const isAdmin = user?.roles?.includes('ROLE_ADMIN');

    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">SkillSync</Link>

                <div className="navbar-menu">
                    {isAuthenticated && (
                        <>
                            <Link to="/dashboard" className="navbar-item">Dashboard</Link>
                            <Link to="/profile" className="navbar-item">Profile</Link>
                            {isAdmin && (
                                <Link to="/admin/users" className="navbar-item">Users</Link>
                            )}
                        </>
                    )}
                </div>

                <div className="navbar-end">
                    {isAuthenticated ? (
                        <div className="user-menu">
                            {user?.picture && (
                                <img
                                    src={user.picture}
                                    alt="Profile"
                                    className="user-avatar"
                                />
                            )}
                            <span className="user-name">{user?.name}</span>
                            <button
                                onClick={logout}
                                className="logout-button"
                            >
                                Logout
                            </button>
                        </div>
                    ) : (
                        <button
                            onClick={() => window.location.href = '/login'}
                            className="login-button"
                        >
                            Login
                        </button>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
