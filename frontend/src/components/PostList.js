import React from 'react';
import PostItem from './PostItem';

export default function PostList({ posts, onEdit, onDelete }) {
    if (!posts.length) return <div>No posts found.</div>;
    return (
        <div className="space-y-4">
            {posts.map(post => (
                <PostItem key={post.id} post={post} onEdit={onEdit} onDelete={onDelete} />
            ))}
        </div>
    );
}
