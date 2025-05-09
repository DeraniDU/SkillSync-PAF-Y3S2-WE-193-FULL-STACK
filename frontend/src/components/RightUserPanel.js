import React, { useEffect, useState } from "react";

// Dummy users for fallback/demo
const DUMMY_USERS = [
    { id: 1, name: "Alice Johnson", email: "alice@example.com" },
    { id: 2, name: "Bob Smith", email: "bob@example.com" },
    { id: 3, name: "Charlie Lee", email: "charlie@example.com" },
    { id: 4, name: "Diana Prince", email: "diana@example.com" },
];

export default function RightUserPanel({ darkMode }) {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        fetch("http://localhost:8080/api/users")
            .then((response) => {
                if (!response.ok) throw new Error("Failed to fetch users");
                return response.json();
            })
            .then((data) => setUsers(data))
            .catch(() => {
                setError("Error loading users. Showing demo users.");
                setUsers(DUMMY_USERS); // Use dummy users on error
            })
            .finally(() => setLoading(false));
    }, []);

    return (
        <div className={`w-64 p-4 border-l ${darkMode ? 'bg-gray-800 border-gray-700 text-white' : 'bg-white border-gray-200 text-gray-900'} h-full`}>
            <h2 className="text-lg font-bold mb-4">Users</h2>
            {loading && <div>Loading...</div>}
            {error && <div className="text-red-500 mb-2">{error}</div>}
            <div className="grid grid-cols-2 gap-4">
                {users.map(user => (
                    <div
                        key={user.id}
                        className={`rounded-lg shadow p-3 flex flex-col items-center ${darkMode ? 'bg-gray-700' : 'bg-gray-100'}`}
                    >
                        <div className="w-12 h-12 rounded-full bg-indigo-400 flex items-center justify-center text-xl font-bold text-white mb-2">
                            {user.name.charAt(0)}
                        </div>
                        <div className="font-medium text-center">{user.name}</div>
                        <div className="text-xs text-center break-words">{user.email}</div>
                    </div>
                ))}
            </div>
            {(!loading && users.length === 0) && (
                <div className="text-sm text-gray-400 mt-4">No users found.</div>
            )}
        </div>
    );
}
