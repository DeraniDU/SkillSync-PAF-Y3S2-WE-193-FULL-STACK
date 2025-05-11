// src/components/PostCardList.jsx
import React, { useEffect, useState } from "react";

const PostCardList = () => {
    const [posts, setPosts] = useState([]);
    const [likes, setLikes] = useState({});

    useEffect(() => {
        fetch("localhost:8083/api/v1/skill-posts")
            .then((res) => res.json())
            .then((data) => {
                const posts = data.content || [];
                setPosts(posts);
                posts.forEach((post) => fetchLikes(post.postId));
            });
    }, []);

    const fetchLikes = (postId) => {
        fetch(`http://localhost:8083/api/v1/likes?postId=${postId}`)
            .then((res) => res.json())
            .then((data) => {
                setLikes((prev) => ({ ...prev, [postId]: data.content.length }));
            });
    };

    const handleLike = (postId, userId = 1) => {
        fetch("http://localhost:8083/api/v1/likes", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ postId, userId })
        })
            .then(() => fetchLikes(postId));
    };

    return (
        <div className="p-4 grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {posts.map((post) => (
                <div
                    key={post.postId}
                    className="bg-white rounded-lg shadow-md p-4 border border-gray-200"
                >
                    <h2 className="text-lg font-semibold mb-2">{post.title}</h2>
                    <p className="text-sm text-gray-700 mb-2">{post.description}</p>
                    <p className="text-xs text-gray-500 mb-4">User ID: {post.userId}</p>
                    <div className="flex justify-between items-center">
                        <button
                            onClick={() => handleLike(post.postId)}
                            className="text-blue-600 hover:text-blue-800"
                        >
                            👍 Like
                        </button>
                        <span className="text-sm text-gray-600">
              {likes[post.postId] || 0} likes
            </span>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default PostCardList;
