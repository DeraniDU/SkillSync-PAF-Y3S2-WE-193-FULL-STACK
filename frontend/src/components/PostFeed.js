// PostFeed.js
import React, { useState } from "react";

export default function PostFeed({ posts, currentUser, handleLike, commentInputs, handleCommentChange, handleCommentSubmit, darkMode }) {
  return (
    <div>
      {posts.length === 0 && <p>No posts found.</p>}

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {posts.map((post) => (
          <div
            key={post.id}
            className={`rounded-lg shadow-md overflow-hidden ${darkMode ? "bg-gray-800" : "bg-white"}`}
          >
            <img src={post.image} alt={post.caption} className="w-full h-48 object-cover" />
            <div className="p-4">
              <h2 className="font-semibold mb-2">{post.caption}</h2>
              <p className="text-sm mb-2 text-gray-500">Posted by {post.ownerName}</p>

              <div className="flex items-center mb-3 space-x-4">
                <button
                  onClick={() => handleLike(post.id)}
                  disabled={post.likedBy.includes(currentUser.id)}
                  className={`px-3 py-1 rounded ${
                    post.likedBy.includes(currentUser.id)
                      ? "bg-gray-500 cursor-not-allowed"
                      : "bg-blue-600 hover:bg-blue-700 text-white"
                  }`}
                >
                  👍 Like ({post.likesCount})
                </button>
              </div>

              {/* Comments */}
              <div className="mb-2 max-h-40 overflow-auto">
                {post.comments.length === 0 && <p className="text-sm italic text-gray-400">No comments yet.</p>}
                {post.comments.map((comment) => (
                  <div key={comment.id} className="text-sm border-b border-gray-300 py-1">
                    <strong>{comment.author}:</strong> {comment.text}
                  </div>
                ))}
              </div>

              {/* Add comment form */}
              <form onSubmit={(e) => handleCommentSubmit(post.id, e)} className="flex space-x-2">
                <input
                  type="text"
                  placeholder="Add a comment..."
                  value={commentInputs[post.id] || ""}
                  onChange={(e) => handleCommentChange(post.id, e.target.value)}
                  className="flex-grow border rounded px-2 py-1 text-black"
                />
                <button type="submit" className="bg-green-600 hover:bg-green-700 text-white px-3 rounded">
                  Comment
                </button>
              </form>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
