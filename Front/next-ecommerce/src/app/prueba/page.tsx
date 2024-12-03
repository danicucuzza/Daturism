'use client';

import { useState, useEffect } from 'react';

interface Destino {
  id_destino: number;
  nombre: string;
  descripcion: string;
  precio: number;
  ubicacion: string;
}

export default function PruebaPage() {
  const [destinos, setDestinos] = useState<Destino[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const getDestinos = async () => {
      try {
        // Usar XMLHttpRequest en lugar de fetch para evitar conflictos con AdBlock
        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8081/api/destinos/traertodos', true);
        
        xhr.onload = function() {
          if (xhr.status === 200) {
            const data = JSON.parse(xhr.responseText);
            console.log('Datos recibidos:', data);
            setDestinos(data);
          } else {
            setError('Error al cargar los datos');
          }
          setLoading(false);
        };

        xhr.onerror = function() {
          setError('Error de conexión');
          setLoading(false);
        };

        xhr.send();
      } catch (err) {
        console.error('Error:', err);
        setError('Error al cargar los datos');
        setLoading(false);
      }
    };

    getDestinos();
  }, []);

  if (loading) {
    return <div className="p-4">Cargando...</div>;
  }

  if (error) {
    return <div className="p-4 text-red-500">{error}</div>;
  }

  return (
    <div className="p-6">
      <h1 className="text-2xl font-bold mb-4">Destinos</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {destinos.map(destino => (
          <div 
            key={destino.id_destino} 
            className="border rounded-lg p-4 shadow"
          >
            <h2 className="text-xl font-bold">{destino.nombre}</h2>
            <p className="text-gray-600">{destino.descripcion}</p>
            <div className="mt-4 flex justify-between items-center">
              <span className="text-lg font-bold text-green-600">
                ${destino.precio.toLocaleString('es-AR')}
              </span>
              <span className="bg-blue-100 text-blue-800 px-2 py-1 rounded">
                {destino.ubicacion}
              </span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}