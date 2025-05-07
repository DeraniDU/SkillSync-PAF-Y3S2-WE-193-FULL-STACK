// src/services/userService.js
import api from '../api/axiosConfig';

export const userService = {
    getCurrentUser: async () => {
        const response = await api.get('/users/me');
        return response.data;
    },

    getUserById: async (id) => {
        const response = await api.get(`/users/${id}`);
        return response.data;
    },

    updateUser: async (id, userData) => {
        const response = await api.put(`/users/${id}`, userData);
        return response.data;
    },

    getAllUsers: async () => {
        const response = await api.get('/admin/users');
        return response.data;
    },

    deleteUser: async (id) => {
        await api.delete(`/admin/users/${id}`);
    }
};
