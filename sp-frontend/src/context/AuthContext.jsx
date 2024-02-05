import React, { createContext, useState, useEffect } from "react";
import jwt_decode from "jwt-decode";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      const decodedToken = jwt_decode(token);
      setUser(decodedToken);
    }
  }, []);

  const register = async (data) => {
    const response = await fetch("http://local:8080/api/auth/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const responseData = await response.json();
    if (responseData.token) {
      localStorage.setItem("token", responseData.token);
      const decodedToken = jwt_decode(responseData.token);
      setUser(decodedToken);
    }
  };

  const login = async (data) => {
    const response = await fetch("http://local:8080/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    const responseData = await response.json();
    if (responseData.token) {
      localStorage.setItem("token", responseData.token);
      const decodedToken = jwt_decode(responseData.token);
      setUser(decodedToken);
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, register, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
