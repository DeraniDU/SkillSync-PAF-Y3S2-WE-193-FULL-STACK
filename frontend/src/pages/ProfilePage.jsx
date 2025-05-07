// src/pages/ProfilePage.jsx
import { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { userService } from '../services/userService';

const ProfilePage = () => {
    const { user, setUser } = useAuth();
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({
        name: user?.name || '',
        phoneNumber: user?.phoneNumber || '',
        address: user?.address || ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const updatedUser = await userService.updateUser(user.id, formData);
            setUser({ ...user, ...updatedUser });
            setIsEditing(false);
        } catch (error) {
            console.error('Failed to update profile:', error);
        }
    };

    return (
        <div className="profile-container">
            <div className="profile-header">
                <h1>My Profile</h1>
                <button
                    onClick={() => setIsEditing(!isEditing)}
                    className="edit-button"
                >
                    {isEditing ? 'Cancel' : 'Edit Profile'}
                </button>
            </div>

            <div className="profile-content">
                {isEditing ? (
                    <form onSubmit={handleSubmit} className="profile-form">
                        <div className="form-group">
                            <label>Name</label>
                            <input
                                type="text"
                                name="name"
                                value={formData.name}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Phone Number</label>
                            <input
                                type="text"
                                name="phoneNumber"
                                value={formData.phoneNumber}
                                onChange={handleChange}
                            />
                        </div>

                        <div className="form-group">
                            <label>Address</label>
                            <input
                                type="text"
                                name="address"
                                value={formData.address}
                                onChange={handleChange}
                            />
                        </div>

                        <button type="submit" className="save-button">
                            Save Changes
                        </button>
                    </form>
                ) : (
                    <div className="profile-details">
                        <div className="detail-group">
                            <h3>Name</h3>
                            <p>{user?.name}</p>
                        </div>

                        <div className="detail-group">
                            <h3>Email</h3>
                            <p>{user?.email}</p>
                        </div>

                        <div className="detail-group">
                            <h3>Phone Number</h3>
                            <p>{user?.phoneNumber || 'Not provided'}</p>
                        </div>

                        <div className="detail-group">
                            <h3>Address</h3>
                            <p>{user?.address || 'Not provided'}</p>
                        </div>

                        <div className="detail-group">
                            <h3>Roles</h3>
                            <p>{user?.roles?.join(', ')}</p>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default ProfilePage;
