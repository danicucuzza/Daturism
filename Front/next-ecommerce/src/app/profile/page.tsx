"use client";
import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';

const ProfilePage = () => {
  const router = useRouter();
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    if (!token) {
      router.push('/login');
      return;
    }

    // Obtener datos del usuario del localStorage
    const user = localStorage.getItem('user');
    if (user) {
      setUserData(JSON.parse(user));
    }
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    router.push('/login');
  };

  if (!userData) return null;

  return (
    <div className="min-h-[calc(100vh-80px)] p-8 bg-gray-50">
      <div className="max-w-3xl mx-auto bg-white rounded-lg shadow-md p-6">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-2xl font-bold text-gray-800">Mi Perfil</h1>
          <button
            onClick={handleLogout}
            className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition-colors"
          >
            Cerrar Sesión
          </button>
        </div>

        <div className="space-y-6">
          <div className="p-4 bg-gray-50 rounded-lg">
            <h2 className="text-lg font-semibold text-gray-800 mb-4">Información Personal</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <p className="text-sm text-gray-500">Nombre</p>
                <p className="font-medium">{userData.nombre}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">Email</p>
                <p className="font-medium">{userData.email}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">Teléfono</p>
                <p className="font-medium">{userData.telefono}</p>
              </div>
              <div>
                <p className="text-sm text-gray-500">Dirección</p>
                <p className="font-medium">{userData.direccion}</p>
              </div>
            </div>
          </div>

          <div className="p-4 bg-gray-50 rounded-lg">
            <h2 className="text-lg font-semibold text-gray-800 mb-4">Mis Reservas</h2>
            <div className="space-y-2">
              {userData.ListaVenta && userData.ListaVenta.length > 0 ? (
                userData.ListaVenta.map((venta, index) => (
                  <div key={index} className="p-3 bg-white rounded border border-gray-200">
                    <p>Reserva #{index + 1}</p>
                  </div>
                ))
              ) : (
                <p className="text-gray-500">No tienes reservas activas</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;