import axios from "axios";
import authHeader from "./auth-header";

const API_URL = process.env.REACT_APP_API_URL;

class UserService {
  getPublicContent() {
    return axios.get(API_URL + "/test/all");
  }

  getUserContent() {
    return axios.get(API_URL + "/test/user", { headers: authHeader() });
  }

  getModeratorContent() {
    return axios.get(API_URL + "/test/mod", { headers: authHeader() });
  }

  getAdminContent() {
    return axios.get(API_URL + "/test/admin", { headers: authHeader() });
  }
}

export default new UserService();
