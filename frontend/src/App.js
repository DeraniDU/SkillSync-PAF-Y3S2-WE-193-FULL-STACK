import React, { useEffect, useState } from "react";

function App() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        setError(null);
        fetch("http://localhost:8080/api/posts")
            .then(response => {
                if (!response.ok) throw new Error("Network response was not ok");
                return response.json();
            })
            .then(data => setPosts(data))
            .catch(error => {
                setError("Failed to fetch posts.");
                console.error("Error fetching posts:", error);
            })
            .finally(() => setLoading(false));
    }, []);

    return (
        <div className="App max-w-4xl mx-auto p-4">
            <h1 className="text-2xl font-bold mb-4">Posts</h1>
            {error && <div className="text-red-500 mb-4">{error}</div>}
            {loading ? (
                <div>Loading...</div>
            ) : posts.length === 0 ? (
                <div className="text-gray-500">No posts found.</div>
            ) : (
                <div className="overflow-x-auto">
                    <table className="min-w-full border">
                        <thead>
                        <tr>
                            <th className="border px-2 py-1">ID</th>
                            <th className="border px-2 py-1">Caption</th>
                            <th className="border px-2 py-1">Prerequisites</th>
                            <th className="border px-2 py-1">Tags</th>
                            <th className="border px-2 py-1">Skills</th>
                        </tr>
                        </thead>
                        <tbody>
                        {posts.map(post => (
                            <tr key={post.id}>
                                <td className="border px-2 py-1">{post.id}</td>
                                <td className="border px-2 py-1">{post.caption}</td>
                                <td className="border px-2 py-1">{post.prerequisites}</td>
                                <td className="border px-2 py-1">{post.tags}</td>
                                <td className="border px-2 py-1">{post.skills}</td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
}

export default App;
