import React, { useState } from 'react';

export default function PostForm({ onSubmit, initialData = {}, loading, onCancel }) {
    const [caption, setCaption] = useState(initialData.caption || '');
    const [prerequisites, setPrerequisites] = useState(initialData.prerequisites || '');
    const [tags, setTags] = useState(initialData.tags || '');
    const [skills, setSkills] = useState(initialData.skills || '');

    const handleSubmit = e => {
        e.preventDefault();
        onSubmit({ caption, prerequisites, tags, skills });
    };

    return (
        <form className="bg-white p-6 rounded shadow-md space-y-4" onSubmit={handleSubmit}>
            <div>
                <label className="block mb-1 font-bold">Caption</label>
                <input className="w-full border p-2 rounded" value={caption} onChange={e => setCaption(e.target.value)} required />
            </div>
            <div>
                <label className="block mb-1 font-bold">Prerequisites</label>
                <input className="w-full border p-2 rounded" value={prerequisites} onChange={e => setPrerequisites(e.target.value)} />
            </div>
            <div>
                <label className="block mb-1 font-bold">Tags (comma separated)</label>
                <input className="w-full border p-2 rounded" value={tags} onChange={e => setTags(e.target.value)} />
            </div>
            <div>
                <label className="block mb-1 font-bold">Skills (comma separated)</label>
                <input className="w-full border p-2 rounded" value={skills} onChange={e => setSkills(e.target.value)} />
            </div>
            <div className="flex space-x-2">
                <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded" disabled={loading}>
                    {loading ? 'Saving...' : 'Submit'}
                </button>
                {onCancel && (
                    <button type="button" className="bg-gray-400 text-white px-4 py-2 rounded" onClick={onCancel}>
                        Cancel
                    </button>
                )}
            </div>
        </form>
    );
}
