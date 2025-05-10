// src/components/PostItem.js
import React from 'react';

export default function PostItem({ post, onEdit, onDelete, onCollaborate, darkMode }) {
    return (
        <div className={`border p-4 rounded shadow flex flex-col gap-4 ${darkMode ? 'bg-gray-800 text-white border-gray-700' : 'bg-white text-gray-900 border-gray-200'}`}>
            <div>
                <div className="font-bold text-lg mb-1">{post.caption}</div>
                <div className="text-sm text-gray-500 mb-1">Prerequisites: {post.prerequisites || "None"}</div>
                <div className="text-sm text-gray-500 mb-1">
                    Tags: {Array.isArray(post.tags) ? post.tags.join(', ') : post.tags}
                </div>
                <div className="text-sm text-gray-500">
                    Skills: {Array.isArray(post.skills) ? post.skills.join(', ') : post.skills}
                </div>
            </div>
            <div className="flex gap-2">
                <button
                    className="bg-green-600 hover:bg-green-700 text-white px-3 py-1 rounded flex-1"
                    onClick={() => onCollaborate(post)}
                >
                    🤝 Ask to Collaborate
                </button>
                <button
                    className="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded flex-1"
                    onClick={() => onEdit(post)}
                >
                    ✏️ Edit
                </button>
                <button
                    className="bg-red-600 hover:bg-red-700 text-white px-3 py-1 rounded flex-1"
                    onClick={() => onDelete(post.id)}
                >
                    🗑️ Delete
                </button>
            </div>
        </div>
    );
}
