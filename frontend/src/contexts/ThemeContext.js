"use client"

import { createContext, useState, useEffect } from "react"

export const ThemeContext = createContext()

export const ThemeProvider = ({ children }) => {
    const [isDarkMode, setIsDarkMode] = useState(() => {
        // Get saved theme from localStorage or use device preference
        const savedTheme = localStorage.getItem("theme")
        if (savedTheme) {
            return savedTheme === "dark"
        }
        // Check for system preference
        return window.matchMedia("(prefers-color-scheme: dark)").matches
    })

    // Update the data-theme attribute and apply Tailwind dark mode
    useEffect(() => {
        // Set data-theme attribute for custom CSS variables
        document.body.setAttribute("data-theme", isDarkMode ? "dark" : "light")

        // Set dark class for Tailwind
        if (isDarkMode) {
            document.documentElement.classList.add("dark")
        } else {
            document.documentElement.classList.remove("dark")
        }

        localStorage.setItem("theme", isDarkMode ? "dark" : "light")
    }, [isDarkMode])

    const toggleTheme = () => {
        setIsDarkMode((prevMode) => !prevMode)
    }

    return <ThemeContext.Provider value={{ isDarkMode, toggleTheme }}>{children}</ThemeContext.Provider>
}
