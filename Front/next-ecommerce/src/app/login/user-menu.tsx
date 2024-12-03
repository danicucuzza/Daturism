"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

const UserMenu = ({ onLogout }) => {
  const [userData, setUserData] = useState(null);
  const router = useRouter();

  useEffect(() => {
    const user = localStorage.getItem('user');
    const token = localStorage.getItem('token');
    
    if (!user || !token) {
      onLogout();
      return;
    }

    setUserData(JSON.parse(user));
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    onLogout();
  };

  if (!userData) return null;

  return (
    <div className="min-h-[calc(100vh-80px)] flex flex-col items-center justify-center">
      <div className="w-full max-w-md p-6 bg-white rounded-lg shadow">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-semibold">Mi Cuenta</h1>
          <button
            onClick={handleLogout}
            className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
          >
            Cerrar Sesión
          </button>
        </div>
        
        <div className="space-y-4">
          {userData.nombre && (
            <div>
              <p className="text-sm text-gray-500">Nombre</p>
              <p className="font-medium">{userData.nombre}</p>
            </div>
          )}
          <div>
            <p className="text-sm text-gray-500">Email</p>
            <p className="font-medium">{userData.email}</p>
          </div>
          {userData.telefono && (
            <div>
              <p className="text-sm text-gray-500">Teléfono</p>
              <p className="font-medium">{userData.telefono}</p>
            </div>
          )}
          {userData.direccion && (
            <div>
              <p className="text-sm text-gray-500">Dirección</p>
              <p className="font-medium">{userData.direccion}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default UserMenu;