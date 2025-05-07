// src/pages/AdminUsersPage.jsx
import { useState, useEffect } from 'react';
import { userService } from '../services/userService';

const AdminUsersPage = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const data = await userService.getAllUsers();
                setUsers(data);
            } catch (error) {
                console.error('Failed to fetch users:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchUsers();
    }, []);

    const handleDeleteUser = async (id) => {
        if (window.confirm('Are you sure you want to delete this user?')) {
            try {
                await userService.deleteUser(id);
                setUsers(users.filter(user => user.id !== id));
            } catch (error) {
                console.error('Failed to delete user:', error);
            }
        }
    };

    if (loading) return <div className="loading">Loading users...</div>;

    return (
        <div className="admin-container">
            <h1>User Management</h1>

            <div className="users-table-container">
                <table className="users-table">
                    <thead>
                    <tr>
                        <th>User</th>
                        <th>Email</th>
                        <th>Roles</th>
                        <th>Phone</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>
                                <div className="user-cell">
                                    {user.picture && (
                                        <img src={user.picture} alt="" className="user-table-img" />
                                    )}
                                    <span>{user.name}</span>
                                </div>
                            </td>
                            <td>{user.email}</td>
                            <td>{user.roles.join(', ')}</td>
                            <td>{user.phoneNumber || 'N/A'}</td>
                            <td>
                                <button
                                    onClick={() => handleDeleteUser(user.id)}
                                    className="delete-button"
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AdminUsersPage;
