"use client"

import { useState, useContext } from "react"
import { ThemeContext } from "../contexts/ThemeContext"

const NotificationButton = () => {
    const [showNotifications, setShowNotifications] = useState(false)
    const { isDarkMode } = useContext(ThemeContext)
    const [notifications, setNotifications] = useState([
        { id: 1, title: "New message", description: "You have a new message", read: false, time: "2 minutes ago" },
        {
            id: 2,
            title: "Profile updated",
            description: "Your profile was updated successfully",
            read: true,
            time: "1 day ago",
        },
        {
            id: 3,
            title: "New login",
            description: "New login detected from Chrome browser",
            read: false,
            time: "3 days ago",
        },
    ])

    const toggleNotifications = () => {
        setShowNotifications(!showNotifications)
    }

    const markAsRead = (id) => {
        setNotifications(
            notifications.map((notification) => (notification.id === id ? { ...notification, read: true } : notification)),
        )
    }

    const unreadCount = notifications.filter((notification) => !notification.read).length

    return (
        <div className="relative">
            <button
                className={`p-2 rounded-full ${
                    isDarkMode ? "bg-gray-800 text-gray-300 hover:bg-gray-700" : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                } transition-colors duration-200 relative`}
                onClick={toggleNotifications}
                aria-label="Notifications"
            >
                <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z" />
                </svg>
                {unreadCount > 0 && (
                    <span className="absolute top-0 right-0 transform translate-x-1/2 -translate-y-1/2 bg-red-500 text-white text-xs font-bold rounded-full h-5 w-5 flex items-center justify-center">
            {unreadCount}
          </span>
                )}
            </button>

            {showNotifications && (
                <div
                    className={`absolute right-0 mt-2 w-80 rounded-md shadow-lg z-50 ${
                        isDarkMode ? "bg-gray-800 text-white" : "bg-white text-gray-900"
                    } ring-1 ring-black ring-opacity-5 focus:outline-none`}
                >
                    <div
                        className={`px-4 py-3 border-b ${isDarkMode ? "border-gray-700" : "border-gray-200"} flex justify-between items-center`}
                    >
                        <h3 className="text-sm font-medium">Notifications</h3>
                        {unreadCount > 0 && (
                            <span
                                className={`text-xs font-medium px-2 py-1 rounded-full ${isDarkMode ? "bg-blue-600" : "bg-blue-100 text-blue-800"}`}
                            >
                {unreadCount} New
              </span>
                        )}
                    </div>
                    <div className="max-h-96 overflow-y-auto">
                        {notifications.length === 0 ? (
                            <div className="py-4 px-3 text-sm text-center">No notifications</div>
                        ) : (
                            notifications.map((notification) => (
                                <div
                                    key={notification.id}
                                    className={`px-4 py-3 ${!notification.read ? (isDarkMode ? "bg-gray-700" : "bg-blue-50") : ""} ${
                                        isDarkMode ? "hover:bg-gray-700" : "hover:bg-gray-50"
                                    } cursor-pointer border-b ${isDarkMode ? "border-gray-700" : "border-gray-200"}`}
                                    onClick={() => markAsRead(notification.id)}
                                >
                                    <div className="flex justify-between">
                                        <p
                                            className={`text-sm font-medium ${notification.read ? (isDarkMode ? "text-gray-300" : "text-gray-700") : isDarkMode ? "text-white" : "text-gray-900"}`}
                                        >
                                            {notification.title}
                                        </p>
                                        <p className={`text-xs ${isDarkMode ? "text-gray-400" : "text-gray-500"}`}>{notification.time}</p>
                                    </div>
                                    <p className={`text-sm mt-1 ${isDarkMode ? "text-gray-400" : "text-gray-600"}`}>
                                        {notification.description}
                                    </p>
                                </div>
                            ))
                        )}
                    </div>
                    <div className={`py-2 px-3 text-center border-t ${isDarkMode ? "border-gray-700" : "border-gray-200"}`}>
                        <button
                            className={`text-sm font-medium ${isDarkMode ? "text-blue-400 hover:text-blue-300" : "text-blue-600 hover:text-blue-800"}`}
                        >
                            View all notifications
                        </button>
                    </div>
                </div>
            )}
        </div>
    )
}

export default NotificationButton
