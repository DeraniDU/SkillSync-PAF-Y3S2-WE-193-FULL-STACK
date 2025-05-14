import React, { useEffect, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";

const Sidebar = () => {
    const [currentUser, setCurrentUser] = useState(null);
    const [expanded, setExpanded] = useState(true);
    const location = useLocation();
    const navigate = useNavigate();

    // Function to load current user
    const loadCurrentUser = () => {
        const user = AuthService.getCurrentUser();
        setCurrentUser(user);
    };

    useEffect(() => {
        // Load user on component mount
        loadCurrentUser();

        // Set up event listener for auth changes
        window.addEventListener("auth-change", loadCurrentUser);

        // Clean up event listener
        return () => {
            window.removeEventListener("auth-change", loadCurrentUser);
        };
    }, []);

    const logOut = () => {
        AuthService.logout();
        setCurrentUser(null);
        // Dispatch custom event to notify auth change
        window.dispatchEvent(new Event("auth-change"));
        navigate("/login");
    };

    const logIn = () => {
        navigate("/login");
    };

    return (
        <div className={`h-screen bg-white dark:bg-gray-800 transition-all duration-300 ${expanded ? "w-64" : "w-20"} fixed left-0 top-0 z-10 border-r border-gray-200 dark:border-gray-700`}>
            {/* Toggle Button */}
            <button
                onClick={() => setExpanded(!expanded)}
                className="absolute -right-3 top-10 bg-white dark:bg-gray-700 rounded-full p-1 shadow-md hover:bg-gray-100 dark:hover:bg-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
            >
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-5 w-5 text-gray-500 dark:text-gray-300"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                >
                    {expanded ? (
                        <path fillRule="evenodd" d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z" clipRule="evenodd" />
                    ) : (
                        <path fillRule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clipRule="evenodd" />
                    )}
                </svg>
            </button>

            {/* User Profile Section - Shown only when logged in */}
            {currentUser ? (
                <div className="flex flex-col items-center py-8 border-b border-gray-200 dark:border-gray-700">
                    <div className="w-14 h-14 rounded-full bg-gradient-to-r from-blue-500 to-indigo-600 flex items-center justify-center text-white font-bold text-xl mb-3 shadow-md">
                        {currentUser.username ? currentUser.username.charAt(0).toUpperCase() : "U"}
                    </div>
                    {expanded && (
                        <div className="text-center px-2">
                            <h5 className="text-base font-semibold text-gray-900 dark:text-white">
                                {currentUser.username}
                            </h5>
                            <span className="text-xs text-gray-500 dark:text-gray-400 truncate block max-w-[90%] mx-auto">
                                {currentUser.email}
                            </span>
                        </div>
                    )}
                </div>
            ) : (
                <div className="flex flex-col items-center py-8 border-b border-gray-200 dark:border-gray-700">
                    <div className="w-14 h-14 rounded-full bg-gray-200 dark:bg-gray-700 flex items-center justify-center text-gray-500 dark:text-gray-400 font-bold text-xl mb-3 shadow-md">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-8 w-8" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                        </svg>
                    </div>
                    {expanded && (
                        <div className="text-center px-2">
                            <h5 className="text-base font-semibold text-gray-900 dark:text-white">
                                Guest User
                            </h5>
                            <span className="text-xs text-gray-500 dark:text-gray-400">
                                Not logged in
                            </span>
                        </div>
                    )}
                </div>
            )}

            {/* Menu Items */}
            <nav className="mt-6 px-3">
                <div className={expanded ? "mb-4 px-3" : "mb-4 text-center"}>
                    <h5 className={`${expanded ? "text-xs" : "text-[10px]"} font-semibold text-gray-400 uppercase tracking-wider`}>
                        {expanded ? "Main Menu" : "Menu"}
                    </h5>
                </div>
                <ul className="space-y-1">
                    {/* Always visible items */}
                    <MenuItem
                        to="/find-contributor"
                        icon={<svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>}
                        text="Find a contributor"
                        expanded={expanded}
                        active={location.pathname === "/find-contributor"}
                    />

                    {/* Items visible only when logged in */}
                    {currentUser && (
                        <>
                            <MenuItem
                                to="/ask-contribute"
                                icon={<svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z" /></svg>}
                                text="Ask to contribute"
                                expanded={expanded}
                                active={location.pathname === "/ask-contribute"}
                            />
                            <MenuItem
                                to="/add-post"
                                icon={<svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 4v16m8-8H4" /></svg>}
                                text="Add Post"
                                expanded={expanded}
                                active={location.pathname === "/add-post"}
                            />
                            <MenuItem
                                to="/saved-posts"
                                icon={<svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 5a2 2 0 012-2h10a2 2 0 012 2v16l-7-3.5L5 21V5z" /></svg>}
                                text="Saved Posts"
                                expanded={expanded}
                                active={location.pathname === "/saved-posts"}
                            />
                        </>
                    )}
                </ul>
            </nav>

            {/* Login/Logout Button */}
            <div className="absolute bottom-8 w-full px-3">
                {currentUser ? (
                    <button
                        onClick={logOut}
                        className={`flex items-center justify-${expanded ? "start" : "center"} w-full p-3 text-red-500 rounded-lg hover:bg-red-50 dark:hover:bg-gray-700 transition-colors duration-200`}
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                        </svg>
                        {expanded && <span className="ml-3 font-medium">Logout</span>}
                    </button>
                ) : (
                    <button
                        onClick={logIn}
                        className={`flex items-center justify-${expanded ? "start" : "center"} w-full p-3 text-blue-500 rounded-lg hover:bg-blue-50 dark:hover:bg-gray-700 transition-colors duration-200`}
                    >
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1" />
                        </svg>
                        {expanded && <span className="ml-3 font-medium">Login</span>}
                    </button>
                )}
            </div>
        </div>
    );
};

// Enhanced MenuItem component with active state
const MenuItem = ({ to, icon, text, expanded, active }) => {
    return (
        <li>
            <Link
                to={to}
                className={`flex items-center justify-${expanded ? "start" : "center"} p-3 rounded-lg transition-colors duration-200
                    ${active
                    ? "bg-blue-50 text-blue-600 dark:bg-gray-700 dark:text-blue-400"
                    : "text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700"
                }`}
            >
                <div className={active ? "text-blue-600 dark:text-blue-400" : ""}>
                    {icon}
                </div>
                {expanded && <span className={`ml-3 ${active ? "font-medium" : ""}`}>{text}</span>}
            </Link>
        </li>
    );
};

export default Sidebar;
