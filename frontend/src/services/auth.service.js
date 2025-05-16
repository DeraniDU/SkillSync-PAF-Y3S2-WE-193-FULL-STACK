import axios from "axios";

const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8080/api";

class AuthService {
  login(username, password) {
    console.log("Attempting login to:", API_URL + "/auth/signin");
    return axios
      .post(API_URL + "/auth/signin", { username, password })
      .then(response => {
        console.log("Login response:", response.data);
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
    localStorage.removeItem("googleToken");
  }

  register(username, email, password) {
    return axios.post(API_URL + "/auth/signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    const userStr = localStorage.getItem("user");
    if (!userStr) return null;
    
    try {
      return JSON.parse(userStr);
    } catch(e) {
      console.error("Error parsing user from localStorage:", e);
      return null;
    }
  }

  // Handle the token received from Google OAuth
  handleGoogleLoginSuccess(token, userData) {
    console.log("Storing Google user data:", userData);
    localStorage.setItem("user", JSON.stringify({
      accessToken: token,
      tokenType: "Bearer",
      ...userData
    }));
  }
}

const authService = new AuthService();
export default authService;
