"use client";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import Login from "./login";
import Register from "./register";
import UserMenu from "./user-menu";

const LoginPage = () => {
  const [view, setView] = useState("login");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const router = useRouter();

  useEffect(() => {
    // Verificar si hay una sesión activa
    const checkSession = () => {
      const token = localStorage.getItem('token');
      const user = localStorage.getItem('user');
      
      if (token && user) {
        setIsLoggedIn(true);
        setView("user-menu");
      }
    };

    // Verificar al cargar y cuando la ventana recibe el foco
    checkSession();
    window.addEventListener('focus', checkSession);
    
    return () => window.removeEventListener('focus', checkSession);
  }, []);

  // Si hay una sesión activa, redirigir al panel de usuario
  if (isLoggedIn) {
    return <UserMenu onLogout={() => {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      setIsLoggedIn(false);
      setView("login");
    }} />;
  }

  return (
    <div>
      {view === "login" && (
        <Login 
          onSwitchToRegister={() => setView("register")}
          onLoginSuccess={() => {
            setIsLoggedIn(true);
            setView("user-menu");
          }}
        />
      )}
      {view === "register" && (
        <Register onSwitchToLogin={() => setView("login")} />
      )}
    </div>
  );
};

export default LoginPage;
