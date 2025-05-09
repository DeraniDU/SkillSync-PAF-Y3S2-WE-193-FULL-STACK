import { useState } from 'react';

export default function NavBar({ darkMode }) {
    return (
        <nav className={`${darkMode ? 'bg-gray-800 text-white' : 'bg-white text-gray-900'} shadow-md`}>
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex items-center justify-center h-16">
                    {/* Center - Logo Only */}
                    <h1 className={`text-xl font-bold ${darkMode ? 'text-indigo-400' : 'text-indigo-600'}`}>SkillSync</h1>
                </div>
            </div>
        </nav>
    );
}
