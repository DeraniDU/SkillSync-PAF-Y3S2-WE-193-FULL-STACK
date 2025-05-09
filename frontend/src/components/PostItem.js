import React from 'react';

export default function PostItem({ post, onEdit, onDelete }) {
    return (
        <div className="border p-4 rounded shadow flex justify-between items-center">
            <div>
                <div className="font-bold">{post.caption}</div>
                <div className="text-sm text-gray-600">Prerequisites: {post.prerequisites}</div>
                <div className="text-sm text-gray-600">Tags: {Array.isArray(post.tags) ? post.tags.join(', ') : post.tags}</div>
                <div className="text-sm text-gray-600">Skills: {Array.isArray(post.skills) ? post.skills.join(', ') : post.skills}</div>
            </div>
            <div className="space-x-2">
                <button className="bg-yellow-500 text-white px-2 py-1 rounded" onClick={() => onEdit(post)}>Edit</button>
                <button className="bg-red-600 text-white px-2 py-1 rounded" onClick={() => onDelete(post.id)}>Delete</button>
            </div>
        </div>
    );
}
