"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";

const Register = ({ onSwitchToLogin }) => {
  const router = useRouter();
  const [formData, setFormData] = useState({
    nombre: "",
    telefono: "",
    direccion: "",
    email: "",
    password: "",
  });
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccessMessage("");

    try {
      const response = await fetch('http://localhost:8081/api/clientes/registro', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...formData,
          role: "CLIENTE"
        })
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Error en el registro');
      }

      setSuccessMessage("Usuario registrado exitosamente. Redirigiendo al login...");
      
      localStorage.setItem('tempUserData', JSON.stringify({
        email: formData.email,
        nombre: formData.nombre,
        telefono: formData.telefono,
        direccion: formData.direccion
      }));

      setTimeout(() => {
        onSwitchToLogin();
      }, 1500);

    } catch (err) {
      setError(err.message || 'Error en el registro');
    }
  };

  const handleLoginClick = (e) => {
    e.preventDefault();
    onSwitchToLogin();
  };

  return (
    <div className="min-h-[calc(100vh-80px)] flex flex-col items-center justify-center">
      <div className="w-full max-w-md p-6">
        <h1 className="text-2xl font-semibold text-center mb-8">Registro</h1>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Nombre completo"
            value={formData.nombre}
            onChange={(e) => setFormData({...formData, nombre: e.target.value})}
            className="w-full p-3 border rounded-md"
            required
          />
          <input
            type="tel"
            placeholder="Teléfono"
            value={formData.telefono}
            onChange={(e) => setFormData({...formData, telefono: e.target.value})}
            className="w-full p-3 border rounded-md"
            required
          />
          <input
            type="text"
            placeholder="Dirección"
            value={formData.direccion}
            onChange={(e) => setFormData({...formData, direccion: e.target.value})}
            className="w-full p-3 border rounded-md"
            required
          />
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
          {successMessage && (
            <p className="text-green-500 text-center text-sm">{successMessage}</p>
          )}
          
          <button
            type="submit"
            className="w-full p-3 bg-blue-500 text-white rounded-md hover:bg-blue-600 transition-colors"
          >
            Registrarse
          </button>
        </form>

        <div className="text-center mt-4">
          <button
            onClick={handleLoginClick}
            className="text-blue-500 hover:underline cursor-pointer"
          >
            ¿Ya tienes una cuenta? Inicia sesión
          </button>
        </div>
      </div>
    </div>
  );
};

export default Register;