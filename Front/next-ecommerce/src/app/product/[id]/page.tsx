"use client";

import { useState, useEffect } from 'react';
import { useParams } from 'next/navigation';
import { useCartStore } from '@/hooks/useCartStore';

const ProductDetail = () => {
  const { id } = useParams();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [showNotification, setShowNotification] = useState(false);
  const addToCart = useCartStore((state) => state.addToCart);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const response = await fetch(`http://localhost:8081/api/destinos/${id}`);
        const data = await response.json();
        setProduct(data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching product:', error);
        setLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  if (loading) {
    return <div>Cargando...</div>;
  }

  if (!product) {
    return <div>Producto no encontrado</div>;
  }

  const handleAddToCart = () => {
    const cartItem = {
      id: product.id_destino.toString(),
      name: product.nombre,
      price: product.precio,
      quantity: 1,
      description: product.descripcion,
      customizations: {
        date: new Date().toISOString().split('T')[0],
        time: product.categoria === 'night' ? "19:00" : "10:00",
        people: 1
      },
      finalPrice: product.precio
    };

    addToCart(cartItem);
    
    // Crear elemento de notificación
    const notification = document.createElement('div');
    notification.className = 'fixed top-4 right-4 bg-green-500 text-white px-6 py-3 rounded-lg shadow-lg z-50 animate-fade-in-out';
    notification.textContent = '¡Producto agregado al carrito!';
    document.body.appendChild(notification);

    // Remover después de 2 segundos
    setTimeout(() => {
      notification.remove();
    }, 2000);
  };

  const isNightProduct = (productName: string) => {
    const nightProducts = [
      "Tango en El Viejo Almacén",
      "Show de Tango en Café de los Angelitos",
      "Tour Nocturno por Buenos Aires",
      "Parque de la Costa de Noche"
    ];
    return nightProducts.includes(productName);
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="relative h-96 rounded-lg overflow-hidden">
          <img
            src={product.imagenUrl}
            alt={product.nombre}
            className="w-full h-full object-cover"
          />
        </div>

        <div className="flex flex-col gap-6">
          <h1 className="text-3xl font-bold">{product.nombre}</h1>
          
          <div className="text-2xl font-semibold text-pink-500">
            ${product.precio?.toLocaleString('es-AR')}
          </div>

          <div className="prose">
            <h3>Descripción</h3>
            <p>{product.descripcion}</p>
          </div>

          <div className="mt-6">
            <p><strong>Ubicación:</strong> {product.ubicacion}</p>
            <p><strong>Duración:</strong> {product.duration}</p>
            <p><strong>Máximo de personas:</strong> {product.maxPeople}</p>
            <p><strong>Horario:</strong> {isNightProduct(product.nombre) ? '19:00' : '10:00'} hs</p>
          </div>

          <button
            onClick={() => {
              const cartItem = {
                id: product.id_destino.toString(),
                name: product.nombre,
                price: product.precio,
                quantity: 1,
                description: product.descripcion,
                customizations: {
                  date: new Date().toISOString().split('T')[0],
                  time: isNightProduct(product.nombre) ? '19:00' : '10:00',
                  people: 1
                },
                finalPrice: product.precio
              };
              useCartStore.getState().addToCart(cartItem);
              
              // Crear elemento de notificación
              const notification = document.createElement('div');
              notification.className = 'fixed top-4 right-4 bg-green-500 text-white px-6 py-3 rounded-lg shadow-lg z-50 animate-fade-in-out';
              notification.textContent = '¡Producto agregado al carrito!';
              document.body.appendChild(notification);

              // Remover después de 2 segundos
              setTimeout(() => {
                notification.remove();
              }, 2000);
            }}
            className="w-full bg-pink-500 text-white py-3 rounded-lg hover:bg-pink-600 transition-colors mt-6"
          >
            Agregar al carrito
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductDetail;