"use client"

import { useState, useEffect, useContext } from "react"
import { Link } from "react-router-dom"
import UserService from "../services/user.service"
import AuthService from "../services/auth.service"
import { ThemeContext } from "../contexts/ThemeContext"

const Home = () => {
  const [content, setContent] = useState("")
  const [currentUser, setCurrentUser] = useState(undefined)
  const { isDarkMode } = useContext(ThemeContext)

  useEffect(() => {
    const user = AuthService.getCurrentUser()
    if (user) {
      setCurrentUser(user)
    }

    UserService.getPublicContent().then(
        (response) => {
          setContent(response.data)
        },
        (error) => {
          // Fix: Handle the error object properly
          let errorMessage
          if (error.response && error.response.data) {
            if (typeof error.response.data === "object") {
              // Convert object to a string representation
              errorMessage = JSON.stringify(error.response.data)
            } else {
              errorMessage = String(error.response.data)
            }
          } else {
            errorMessage = error.message || "Unknown error occurred"
          }

          setContent(errorMessage)
        },
    )
  }, [])

  return (
      <div className={`container mx-auto px-4 py-8 ${isDarkMode ? "text-white" : "text-gray-900"}`}>
        <div className={`rounded-lg shadow-md overflow-hidden ${isDarkMode ? "bg-gray-800" : "bg-white"}`}>
          <div className={`px-6 py-8 ${isDarkMode ? "bg-gray-700" : "bg-gray-100"}`}>
            <h2 className="text-3xl font-bold mb-4">{content}</h2>
            <div className="mt-4 space-y-4">
              <p className={`text-lg ${isDarkMode ? "text-gray-300" : "text-gray-700"}`}>
                Welcome to our authentication demo application!
              </p>
              <p className={`${isDarkMode ? "text-gray-300" : "text-gray-700"}`}>
                This page is publicly accessible to everyone.
              </p>

              {!currentUser && (
                  <div className="mt-6 flex flex-col sm:flex-row gap-4">
                    <Link
                        to="/login"
                        className={`inline-flex justify-center items-center px-6 py-3 rounded-md text-base font-medium ${
                            isDarkMode ? "bg-blue-600 hover:bg-blue-700 text-white" : "bg-blue-500 hover:bg-blue-600 text-white"
                        } transition-colors duration-200`}
                    >
                      Login
                    </Link>
                    <Link
                        to="/register"
                        className={`inline-flex justify-center items-center px-6 py-3 rounded-md text-base font-medium ${
                            isDarkMode
                                ? "bg-gray-700 hover:bg-gray-600 text-white"
                                : "bg-gray-200 hover:bg-gray-300 text-gray-900"
                        } transition-colors duration-200`}
                    >
                      Register
                    </Link>
                  </div>
              )}
            </div>
          </div>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-8">
          <FeatureCard
              title="Secure Authentication"
              description="Our platform provides secure authentication with JWT tokens and OAuth2 integration."
              icon={
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-12 w-12"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                >
                  <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M12 15v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2zm10-10V7a4 4 0 00-8 0v4h8z"
                  />
                </svg>
              }
              isDarkMode={isDarkMode}
          />
          <FeatureCard
              title="User Management"
              description="Easily manage user profiles, roles, and permissions with our intuitive interface."
              icon={
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-12 w-12"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                >
                  <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z"
                  />
                </svg>
              }
              isDarkMode={isDarkMode}
          />
          <FeatureCard
              title="Dark Mode Support"
              description="Enjoy a comfortable viewing experience with our fully integrated dark mode support."
              icon={
                <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-12 w-12"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke="currentColor"
                >
                  <path
                      strokeLinecap="round"
                      strokeLinejoin="round"
                      strokeWidth={2}
                      d="M20.354 15.354A9 9 0 018.646 3.646 9.003 9.003 0 0012 21a9.003 9.003 0 008.354-5.646z"
                  />
                </svg>
              }
              isDarkMode={isDarkMode}
          />
        </div>
      </div>
  )
}

// Feature Card Component
const FeatureCard = ({ title, description, icon, isDarkMode }) => {
  return (
      <div className={`rounded-lg shadow-md overflow-hidden ${isDarkMode ? "bg-gray-800" : "bg-white"}`}>
        <div className="p-6">
          <div className={`${isDarkMode ? "text-blue-400" : "text-blue-500"} mb-4`}>{icon}</div>
          <h3 className={`text-xl font-bold mb-2 ${isDarkMode ? "text-white" : "text-gray-900"}`}>{title}</h3>
          <p className={`${isDarkMode ? "text-gray-300" : "text-gray-700"}`}>{description}</p>
        </div>
      </div>
  )
}

export default Home
