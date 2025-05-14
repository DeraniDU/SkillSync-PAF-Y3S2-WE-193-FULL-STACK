import React, { useContext } from 'react';
import { ThemeContext } from '../contexts/ThemeContext';

const DarkModeToggle = () => {
    const { isDarkMode, toggleTheme } = useContext(ThemeContext);

    return (
        <div className="dark-mode-toggle ms-3">
            <button
                onClick={toggleTheme}
                className="btn btn-sm"
                style={{ backgroundColor: 'transparent', border: 'none' }}
                aria-label="Toggle dark mode"
            >
                {isDarkMode ? (
                    <span role="img" aria-label="Light mode" style={{ fontSize: '1.2rem' }}>🔆</span>
                ) : (
                    <span role="img" aria-label="Dark mode" style={{ fontSize: '1.2rem' }}>🌙</span>
                )}
            </button>
        </div>
    );
};

export default DarkModeToggle;
