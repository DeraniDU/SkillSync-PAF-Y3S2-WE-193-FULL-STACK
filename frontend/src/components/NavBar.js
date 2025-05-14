import React from "react";
import { Link } from "react-router-dom";
import DarkModeToggle from "./DarkModeToggle";
import NotificationButton from "./NotificationButton";

const NavBar = () => {
  return (
      <nav className="bg-white shadow-md dark:bg-gray-900 sticky top-0 z-20 w-full">
        <div className="w-full px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between h-16">
            {/* Left section */}
            <div className="flex-1 flex items-center justify-start">
              <button
                  type="button"
                  className="p-2 rounded-md text-gray-500 hover:text-gray-700 hover:bg-gray-100 dark:text-gray-400 dark:hover:text-white dark:hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-blue-500 md:hidden"
              >
                <span className="sr-only">Open menu</span>
                <svg className="h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 6h16M4 12h16M4 18h16" />
                </svg>
              </button>
            </div>

            {/* Center section with logo */}
            <div className="flex-1 flex justify-center">
              <Link to={"/"} className="flex items-center">
                <span className="text-2xl font-bold text-blue-600 dark:text-blue-400">Skill</span>
                <span className="text-2xl font-bold text-gray-800 dark:text-white">Sync</span>
              </Link>
            </div>

            {/* Right section with dark mode toggle and notification */}
            <div className="flex-1 flex justify-end items-center space-x-4">
              <NotificationButton />
              <DarkModeToggle />
            </div>
          </div>
        </div>
      </nav>
  );
};

export default NavBar;