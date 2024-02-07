// UserProfile.js
import React, { useContext } from "react";
import { AuthContext } from "./AuthContext";

const UserProfile = () => {
  const { user, logout } = useContext(AuthContext);

  return (
    <div>
      <h1>Perfil del Usuario</h1>
      {user ? (
        <>
          <p>Nombre de usuario: {user.username}</p>
          <p>Email: {user.email}</p>
          <button onClick={logout}>Cerrar sesión</button>
        </>
      ) : (
        <p>No has iniciado sesión.</p>
      )}
    </div>
  );
};
