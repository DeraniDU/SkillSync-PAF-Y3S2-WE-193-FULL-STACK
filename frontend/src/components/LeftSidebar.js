import {
  Home,
  Users,
  Bookmark,
  PlusCircle,
  Bell,
  LogOut,
  Sun,
  Moon,
  User,
} from "lucide-react";

export default function LeftSidebar({
  darkMode,
  toggleDarkMode,
  onAddClick,
  onNotificationClick,
}) {
  return (
    <div
      className={`w-64 p-4 fixed h-full ${
        darkMode ? "bg-gray-800" : "bg-white"
      } shadow-lg`}
    >
      <div className="mb-8 flex items-center space-x-3">
        <div className="w-10 h-10 bg-blue-500 rounded-full flex items-center justify-center text-white">
          <User size={20} />
        </div>
        <div>
          <h2 className="font-bold">My Profile</h2>
          <p className={`text-sm ${darkMode ? "text-gray-400" : "text-gray-500"}`}>
            @username
          </p>
        </div>
      </div>

      <nav>
        <ul className="space-y-4">
          <li>
            <a
              href="#"
              className={`flex items-center space-x-3 p-2 rounded-lg ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Home size={20} className="text-blue-500" />
              <span>Home</span>
            </a>
          </li>
          <li>
            <a
              href="#"
              className={`flex items-center space-x-3 p-2 rounded-lg ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Users size={20} className="text-blue-500" />
              <span>Find Contributors</span>
            </a>
          </li>
          <li>
            <a
              href="#"
              className={`flex items-center space-x-3 p-2 rounded-lg ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Users size={20} className="text-blue-500" />
              <span>Ask To Collaborate</span>
            </a>
          </li>
          <li>
            <a
              href="#"
              className={`flex items-center space-x-3 p-2 rounded-lg ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Bookmark size={20} className="text-blue-500" />
              <span>Saved People</span>
            </a>
          </li>
          <li>
            {/* "Add Something" as a button */}
            <button
              type="button"
              onClick={onAddClick}
              className={`flex items-center space-x-3 p-2 rounded-lg w-full text-left ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <PlusCircle size={20} className="text-blue-500" />
              <span>Add Something</span>
            </button>
          </li>
          <li>
            <a
              href="#"
              className={`flex items-center space-x-3 p-2 rounded-lg ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Bookmark size={20} className="text-blue-500" />
              <span>Saved</span>
            </a>
          </li>
          <li>
            {/* Notification span click triggers onNotificationClick */}
            <button
              type="button"
              onClick={onNotificationClick}
              className={`flex items-center space-x-3 p-2 rounded-lg w-full text-left ${
                darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
              } font-medium`}
            >
              <Bell size={20} className="text-blue-500" />
              <span>Notifications</span>
            </button>
          </li>
        </ul>
      </nav>

      <div className="absolute bottom-8 w-full pr-8">
        <button
          onClick={toggleDarkMode}
          className={`flex items-center space-x-3 p-2 rounded-lg ${
            darkMode ? "hover:bg-gray-700" : "hover:bg-gray-200"
          } mb-4 w-full`}
        >
          {darkMode ? (
            <>
              <Sun size={20} className="text-yellow-400" />
              <span>Light Mode</span>
            </>
          ) : (
            <>
              <Moon size={20} className="text-blue-500" />
              <span>Dark Mode</span>
            </>
          )}
        </button>

        <button
          className={`flex items-center space-x-3 p-2 rounded-lg ${
            darkMode ? "hover:bg-gray-700 text-red-400" : "hover:bg-gray-200 text-red-500"
          } w-full`}
        >
          <LogOut size={20} />
          <span>Logout</span>
        </button>
      </div>
    </div>
  );
}
