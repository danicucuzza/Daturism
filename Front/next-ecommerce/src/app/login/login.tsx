"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";

const Login = ({ onSwitchToRegister, onLoginSuccess }) => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const router = useRouter();

  // Verificar si ya hay una sesión activa al cargar el componente
  useEffect(() => {
    const token = localStorage.getItem('token');
    const user = localStorage.getItem('user');
    
    if (token && user) {
      onLoginSuccess();
    }
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await fetch('http://localhost:8081/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.mensaje || 'Error en el inicio de sesión');
      }

      if (data.token) {
        // Obtener los datos completos del usuario
        const userResponse = await fetch(`http://localhost:8081/api/clientes/buscar-por-email/${formData.email}`, {
          headers: {
            'Authorization': `Bearer ${data.token}`
          }
        });
        
        const userData = await userResponse.json();

        localStorage.setItem('token', data.token);
        localStorage.setItem('user', JSON.stringify({
          email: userData.email,
          nombre: userData.nombre,
          telefono: userData.telefono,
          direccion: userData.direccion
        }));
        
        onLoginSuccess();
      }
    } catch (err) {
      setError(err.message || 'Error en las credenciales');
    }
  };

  return (
    <div className="min-h-[calc(100vh-80px)] flex flex-col items-center justify-center">
      <div className="w-full max-w-md p-6">
        <h1 className="text-2xl font-semibold text-center mb-8">Iniciar Sesión</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="email"
            placeholder="Email"
            value={formData.email}
            onChange={(e) => setFormData({...formData, email: e.target.value})}
            className="w-full p-3 border rounded-md"
            required
          />
          <input
            type="password"
            placeholder="Contraseña"
            value={formData.password}
            onChange={(e) => setFormData({...formData, password: e.target.value})}
            className="w-full p-3 border rounded-md"
            required
          />
          
          {error && (
            <p className="text-red-500 text-center text-sm">{error}</p>
          )}
          
          <button
            type="submit"
            className="w-full p-3 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors"
          >
            Iniciar Sesión
          </button>
        </form>

        <div className="text-center mt-4">
          <button
            onClick={onSwitchToRegister}
            className="text-blue-500 hover:underline cursor-pointer"
          >
            ¿No tienes una cuenta? Regístrate
          </button>
        </div>
      </div>
    </div>
  );
};

export default Login;