// src/components/PostForm.jsx
import React, { useState } from "react";

const PostCard = ({ onSubmit, loading, onCancel }) => {
    const [formData, setFormData] = useState({
        title: "",
        description: "",
        userId: ""
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4">
            <div>
                <label className="block font-semibold mb-1">Title</label>
                <input
                    className="border p-2 w-full rounded"
                    name="title"
                    value={formData.title}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label className="block font-semibold mb-1">Description</label>
                <textarea
                    className="border p-2 w-full rounded"
                    name="description"
                    value={formData.description}
                    onChange={handleChange}
                    required
                ></textarea>
            </div>
            <div>
                <label className="block font-semibold mb-1">User ID</label>
                <input
                    className="border p-2 w-full rounded"
                    name="userId"
                    type="number"
                    value={formData.userId}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="flex justify-end gap-2">
                <button
                    type="button"
                    className="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded"
                    onClick={onCancel}
                >
                    Cancel
                </button>
                <button
                    type="submit"
                    disabled={loading}
                    className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
                >
                    {loading ? "Saving..." : "Add Post"}
                </button>
            </div>
        </form>
    );
};

export default PostCard;
