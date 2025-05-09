import React, { useEffect, useState } from "react";
import LeftSidebar from "./components/LeftSidebar";
import NavBar from "./components/NavBar";
import PostForm from "./components/PostForm";

// Right user panel component
function RightUserPanel({ darkMode }) {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        setError(null);
        fetch("http://localhost:8080/api/users")
            .then((response) => {
                if (!response.ok) throw new Error("Failed to fetch users");
                return response.json();
            })
            .then((data) => setUsers(data))
            .catch(() => setError("Failed to load users"))
            .finally(() => setLoading(false));
    }, []);

    return (
        <div className={`w-64 p-4 border-l ${darkMode ? 'bg-gray-800 border-gray-700 text-white' : 'bg-white border-gray-200 text-gray-900'} h-full`}>
            <h2 className="text-lg font-bold mb-4">Users</h2>
            {loading && <div>Loading...</div>}
            {error && <div className="text-red-500">{error}</div>}
            <div className="grid grid-cols-2 gap-4">
                {users.map(user => (
                    <div
                        key={user.id}
                        className={`rounded-lg shadow p-3 flex flex-col items-center ${darkMode ? 'bg-gray-700' : 'bg-gray-100'}`}
                    >
                        <div className="w-12 h-12 rounded-full bg-indigo-400 flex items-center justify-center text-xl font-bold text-white mb-2">
                            {user.name ? user.name.charAt(0) : "U"}
                        </div>
                        <div className="font-medium text-center">{user.name}</div>
                        <div className="text-xs text-center break-words">{user.email}</div>
                    </div>
                ))}
            </div>
            {(!loading && users.length === 0) && <div className="text-sm text-gray-400 mt-4">No users found.</div>}
        </div>
    );
}

function App() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [darkMode, setDarkMode] = useState(false);

    // For editing
    const [editingPost, setEditingPost] = useState(null);
    const [editForm, setEditForm] = useState({
        caption: "",
        prerequisites: "",
        tags: "",
        skills: "",
    });

    // For adding
    const [showAddModal, setShowAddModal] = useState(false);
    const [addLoading, setAddLoading] = useState(false);

    // Fetch posts on mount
    useEffect(() => {
        fetchPosts();
    }, []);

    // Fetch all posts
    const fetchPosts = () => {
        setLoading(true);
        setError(null);
        fetch("http://localhost:8080/api/posts")
            .then((response) => {
                if (!response.ok) throw new Error("Network response was not ok");
                return response.json();
            })
            .then((data) => setPosts(data))
            .catch(() => {
                setError("Failed to fetch posts.");
                setPosts([]);
            })
            .finally(() => setLoading(false));
    };

    // Delete post
    const handleDelete = (id) => {
        if (!window.confirm("Are you sure you want to delete this post?")) return;
        setLoading(true);
        fetch(`http://localhost:8080/api/posts/${id}`, {
            method: "DELETE",
        })
            .then((response) => {
                if (!response.ok) throw new Error("Delete failed");
                fetchPosts();
            })
            .catch(() => {
                setError("Failed to delete post.");
                setLoading(false);
            });
    };

    // Open edit modal
    const handleEdit = (post) => {
        setEditingPost(post);
        setEditForm({
            caption: post.caption,
            prerequisites: post.prerequisites,
            tags: post.tags,
            skills: post.skills,
        });
    };

    // Handle edit form changes
    const handleEditChange = (e) => {
        setEditForm({ ...editForm, [e.target.name]: e.target.value });
    };

    // Submit edit
    const handleEditSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        fetch(`http://localhost:8080/api/posts/${editingPost.id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(editForm),
        })
            .then((response) => {
                if (!response.ok) throw new Error("Update failed");
                setEditingPost(null);
                fetchPosts();
            })
            .catch(() => {
                setError("Failed to update post.");
                setLoading(false);
            });
    };

    // Add post
    const handleAddPost = (formData) => {
        setAddLoading(true);
        fetch("http://localhost:8080/api/posts", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData),
        })
            .then((response) => {
                if (!response.ok) throw new Error("Failed to create post");
                return response.json();
            })
            .then(() => {
                setShowAddModal(false);
                fetchPosts();
            })
            .catch(() => setError("Failed to create post."))
            .finally(() => setAddLoading(false));
    };

    // Close modals
    const closeModal = () => setEditingPost(null);

    // Toggle dark mode
    const toggleDarkMode = () => {
        setDarkMode(!darkMode);
    };

    return (
        <div className={`flex flex-col min-h-screen ${darkMode ? 'bg-gray-900 text-white' : 'bg-gray-100 text-gray-900'}`}>
            <NavBar darkMode={darkMode} />

            <div className="flex flex-1">
                <LeftSidebar
                    darkMode={darkMode}
                    toggleDarkMode={toggleDarkMode}
                    onAddClick={() => setShowAddModal(true)}
                />

                <div className="ml-64 flex-grow p-6">
                    <h1 className="text-2xl font-bold mb-6">Posts</h1>
                    {error && <div className="text-red-500 mb-4 p-3 rounded bg-red-100">{error}</div>}
                    {loading && <div className="flex justify-center p-6"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div></div>}
                    {!loading && posts.length === 0 && (
                        <div className={`text-center py-8 ${darkMode ? 'text-gray-400' : 'text-gray-500'}`}>No posts found.</div>
                    )}

                    {/* Card Grid */}
                    {!loading && posts.length > 0 && (
                        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                            {posts.map((post) => (
                                <div key={post.id} className={`rounded-lg shadow-md overflow-hidden ${darkMode ? 'bg-gray-800' : 'bg-white'}`}>
                                    <div className="p-5">
                                        <div className="flex justify-between items-center mb-3">
                                            <h2 className="text-lg font-semibold truncate">{post.caption}</h2>
                                            <div className="text-xs px-2 py-1 rounded-full bg-blue-100 text-blue-800">ID: {post.id}</div>
                                        </div>
                                        <div className={`mb-3 ${darkMode ? 'text-gray-300' : 'text-gray-600'}`}>
                                            <h3 className="font-medium mb-1">Prerequisites:</h3>
                                            <p className="text-sm">{post.prerequisites || "None"}</p>
                                        </div>
                                        <div className="mb-4">
                                            <div className="flex flex-wrap gap-2 mb-2">
                                                <h3 className="font-medium mr-2">Tags:</h3>
                                                {post.tags ? post.tags.split(',').map((tag, index) => (
                                                    <span key={index} className={`text-xs px-2 py-1 rounded-full ${darkMode ? 'bg-gray-700 text-gray-300' : 'bg-gray-200'}`}>
                                                        {tag.trim()}
                                                    </span>
                                                )) : <span className="text-sm text-gray-500">No tags</span>}
                                            </div>
                                            <div className="flex flex-wrap gap-2">
                                                <h3 className="font-medium mr-2">Skills:</h3>
                                                {post.skills ? post.skills.split(',').map((skill, index) => (
                                                    <span key={index} className={`text-xs px-2 py-1 rounded-full ${darkMode ? 'bg-blue-900 text-blue-100' : 'bg-blue-100 text-blue-800'}`}>
                                                        {skill.trim()}
                                                    </span>
                                                )) : <span className="text-sm text-gray-500">No skills</span>}
                                            </div>
                                        </div>
                                    </div>
                                    <div className={`flex border-t ${darkMode ? 'border-gray-700' : 'border-gray-200'}`}>
                                        <button
                                            className={`flex-1 py-3 flex justify-center items-center gap-2 ${darkMode ? 'hover:bg-gray-700' : 'hover:bg-gray-100'} transition-colors`}
                                            onClick={() => handleEdit(post)}
                                        >
                                            <span className="text-yellow-500">✏️</span>
                                            <span>Edit</span>
                                        </button>
                                        <div className={`w-px h-full ${darkMode ? 'bg-gray-700' : 'bg-gray-200'}`}></div>
                                        <button
                                            className={`flex-1 py-3 flex justify-center items-center gap-2 ${darkMode ? 'hover:bg-gray-700' : 'hover:bg-gray-100'} transition-colors`}
                                            onClick={() => handleDelete(post.id)}
                                        >
                                            <span className="text-red-500">🗑️</span>
                                            <span>Delete</span>
                                        </button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    )}

                    {/* Edit Modal */}
                    {editingPost && (
                        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40 z-50">
                            <div className={`rounded-lg shadow-lg p-6 w-full max-w-md ${darkMode ? 'bg-gray-800' : 'bg-white'}`}>
                                <h2 className="text-xl font-bold mb-4">Edit Post</h2>
                                <form onSubmit={handleEditSubmit} className="space-y-3">
                                    <div>
                                        <label className="block font-semibold mb-1">Caption</label>
                                        <input
                                            className={`border p-2 w-full rounded ${darkMode ? 'bg-gray-700 border-gray-600 text-white' : 'bg-white border-gray-300'}`}
                                            name="caption"
                                            value={editForm.caption}
                                            onChange={handleEditChange}
                                            required
                                        />
                                    </div>
                                    <div>
                                        <label className="block font-semibold mb-1">Prerequisites</label>
                                        <input
                                            className={`border p-2 w-full rounded ${darkMode ? 'bg-gray-700 border-gray-600 text-white' : 'bg-white border-gray-300'}`}
                                            name="prerequisites"
                                            value={editForm.prerequisites}
                                            onChange={handleEditChange}
                                        />
                                    </div>
                                    <div>
                                        <label className="block font-semibold mb-1">Tags (comma separated)</label>
                                        <input
                                            className={`border p-2 w-full rounded ${darkMode ? 'bg-gray-700 border-gray-600 text-white' : 'bg-white border-gray-300'}`}
                                            name="tags"
                                            value={editForm.tags}
                                            onChange={handleEditChange}
                                            placeholder="tag1, tag2, tag3"
                                        />
                                    </div>
                                    <div>
                                        <label className="block font-semibold mb-1">Skills (comma separated)</label>
                                        <input
                                            className={`border p-2 w-full rounded ${darkMode ? 'bg-gray-700 border-gray-600 text-white' : 'bg-white border-gray-300'}`}
                                            name="skills"
                                            value={editForm.skills}
                                            onChange={handleEditChange}
                                            placeholder="skill1, skill2, skill3"
                                        />
                                    </div>
                                    <div className="flex justify-end space-x-2 pt-4">
                                        <button
                                            type="button"
                                            className={`px-4 py-2 rounded ${darkMode ? 'bg-gray-700 hover:bg-gray-600' : 'bg-gray-300 hover:bg-gray-400'}`}
                                            onClick={closeModal}
                                        >
                                            Cancel
                                        </button>
                                        <button
                                            type="submit"
                                            className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
                                        >
                                            Save
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    )}

                    {/* Add Modal */}
                    {showAddModal && (
                        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-40 z-50">
                            <div className={`rounded-lg shadow-lg p-6 w-full max-w-md ${darkMode ? 'bg-gray-800' : 'bg-white'}`}>
                                <h2 className="text-xl font-bold mb-4">Add Post</h2>
                                <PostForm
                                    onSubmit={handleAddPost}
                                    loading={addLoading}
                                    onCancel={() => setShowAddModal(false)}
                                />
                            </div>
                        </div>
                    )}
                </div>

                {/* Right User Panel */}
                <RightUserPanel darkMode={darkMode} />
            </div>
        </div>
    );
}

export default App;
