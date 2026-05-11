// save token, username và role vào localStorage
const saveToken = (token) => {
    localStorage.setItem("token", token);
};

const saveUsername = (username) => {
    localStorage.setItem("username", username);
};

const saveRole = (role) => {
    localStorage.setItem("role", role);
};

const saveAuthData = (token, username, role) => {
    saveToken(token);
    saveUsername(username);
    saveRole(role);
};

// Lấy token, username và role từ localStorage
const getToken = () => {
    return localStorage.getItem("token");
}; 

const getUsername = () => {
    return localStorage.getItem("username");
};

const getRole = () => {
    return localStorage.getItem("role");
};

// Xóa token, username và role khỏi localStorage
const removeToken = () => {
    localStorage.removeItem("token");
};

const removeUsername = () => {
    localStorage.removeItem("username");
};

const removeRole = () => {
    localStorage.removeItem("role");
}

const clearAuthData = () => {
    removeToken();
    removeUsername();
    removeRole();
};

// Kiểm tra xem người dùng đã đăng nhập hay chưa
const isSignedIn = () => {
    return !!getToken();
};

export { 
    saveToken, 
    getToken, 
    saveUsername, 
    getUsername, 
    saveRole, 
    getRole, 
    removeToken, 
    removeUsername, 
    removeRole, 
    clearAuthData, 
    isSignedIn, 
    saveAuthData 
};