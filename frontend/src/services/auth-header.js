export default function authHeader() {
  const userStr = localStorage.getItem("user");
  let user = null;
  
  if (userStr) {
    try {
      user = JSON.parse(userStr);
    } catch(e) {
      console.error("Error parsing user from localStorage:", e);
      return {};
    }
  }

  if (user && user.accessToken) {
    console.log("Adding auth header with token:", user.accessToken.substring(0, 20) + "...");
    return { Authorization: "Bearer " + user.accessToken };
  } else {
    console.log("No token found in localStorage");
    return {};
  }
}
