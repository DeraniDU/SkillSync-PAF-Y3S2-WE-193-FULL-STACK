import React from "react";

export default function NotificationsPanel({ notifications, darkMode }) {
  return (
    <div
      className={`fixed bottom-4 right-4 w-96 max-h-96 overflow-auto rounded-lg shadow-lg p-4 border ${
        darkMode ? "bg-gray-800 border-gray-700 text-white" : "bg-white border-gray-300 text-gray-900"
      } z-50`}
    >
      <h3 className="font-semibold mb-4 text-lg">Notifications</h3>

      {/* If no notifications */}
      {notifications.length === 0 && (
        <p className="text-sm text-gray-500">No notifications</p>
      )}

      {/* Notifications List */}
      <div className="grid grid-cols-1 gap-4">
        {notifications.map((notif, i) => (
          <div
            key={i}
            className={`rounded-lg shadow-md overflow-hidden p-4 ${
              darkMode ? "bg-gray-700 text-gray-300" : "bg-gray-100 text-gray-800"
            }`}
          >
            <div className="flex items-center mb-3">
              <strong className="font-semibold">{notif.fromUser}</strong>
            </div>
            <p className="text-sm mb-3">{notif.message}</p>
            <div className="text-xs text-gray-500">{new Date(notif.timestamp).toLocaleTimeString()}</div>
          </div>
        ))}
      </div>
    </div>
  );
}
