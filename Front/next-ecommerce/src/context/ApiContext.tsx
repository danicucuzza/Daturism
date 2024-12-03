"use client"
import { createContext, useContext, useState, useEffect } from "react";

interface Product {
  id_destino: number;
  nombre: string;
  descripcion: string;
  precio: number;
  ubicacion: string;
  imagenUrl: string;
  maxPeople: number;
  duration: string;
}

export const ProductContext = createContext<any>(null);

export const useProducts = () => {
  const context = useContext(ProductContext);
  if (!context) {
    throw new Error("useProducts must be used within a ProductProvider");
  }
  return context;
};

export const ProductProvider = ({ children }: { children: React.ReactNode }) => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    console.log('ProductProvider montado'); // Debug

    const getProducts = () => {
      console.log('Iniciando petición XMLHttpRequest'); // Debug
      
      const xhr = new XMLHttpRequest();
      xhr.open('GET', 'http://localhost:8081/api/destinos/traertodos', true);
      
      xhr.onload = function() {
        console.log('Status:', xhr.status); // Debug
        if (xhr.status === 200) {
          const data = JSON.parse(xhr.responseText);
          console.log('Datos recibidos:', data); // Debug
          setProducts(data);
        } else {
          console.error('Error al cargar productos:', xhr.statusText);
        }
        setLoading(false);
      };

      xhr.onerror = function() {
        console.error('Error de conexión');
        setLoading(false);
      };

      xhr.send();
    };

    getProducts();
  }, []);

  console.log('Estado actual:', { products, loading }); // Debug

  return (
    <ProductContext.Provider value={{ products, loading }}>
      {children}
    </ProductContext.Provider>
  );
};