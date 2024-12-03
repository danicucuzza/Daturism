"use client";

import Link from "next/link";
import Image from 'next/image';
import { useProducts } from '@/context/ApiContext';
import { useCartStore } from '../hooks/useCartStore';

const ProductList = ({ type, category }: { type?: 'featured' | 'new', category?: 'day' | 'night' }) => {
  const { products, loading } = useProducts();
  const addToCart = useCartStore((state) => state.addToCart);

  const formatMaxPeople = (maxPeople: number) => {
    return `${maxPeople} personas`;
  };

  const formatDuration = (duration: string) => {
    return duration;
  };

  if (loading) {
    return <div className="mt-12 text-center">Cargando experiencias...</div>;
  }

  if (!products || products.length === 0) {
    return <div className="mt-12 text-center">No hay experiencias disponibles.</div>;
  }

  // Definir qué productos son de día y de noche
  const dayProducts = [
    "Delta del Tigre con Brunch",
    "Paseo en Barco por Puerto Madero",
    "City Tour por Buenos Aires",
    "Navegación por el Delta del Tigre",
    "Tour Histórico por el Casco Antiguo",
    "Recorrido por Caminito y La Boca",
    "Experiencia de Asado Tradicional",
    "Masterclass de Mate con Merienda"
  ];

  const nightProducts = [
    "Tango en El Viejo Almacén",
    "Show de Tango en Café de los Angelitos",
    "Tour Nocturno por Buenos Aires",
    "Parque de la Costa"
  ];

  // Filtrar productos según tipo y categoría
  const filteredProducts = products.filter(product => {
    // Primero filtrar por featured/new si existe
    if (type === 'featured') {
      return (
        product.nombre === "Tango en El Viejo Almacén" || 
        product.nombre === "Experiencia de Asado Tradicional" ||
        product.nombre === "Masterclass de Mate con Merienda"
      );
    }
    
    if (type === 'new') {
      return !(
        product.nombre === "Tango en El Viejo Almacén" || 
        product.nombre === "Experiencia de Asado Tradicional" ||
        product.nombre === "Masterclass de Mate con Merienda"
      );
    }

    // Luego filtrar por categoría si existe
    if (category === 'day') {
      return dayProducts.includes(product.nombre);
    }
    
    if (category === 'night') {
      return nightProducts.includes(product.nombre);
    }

    return true;
  });

  const handleAddToCart = (product: any) => {
    const cartItem = {
      id: product.id_destino.toString(),
      name: product.nombre,
      price: product.precio,
      quantity: 1,
      description: product.descripcion,
      customizations: {
        date: new Date().toISOString().split('T')[0],
        time: "10:00",
        people: 1
      },
      finalPrice: product.precio
    };

    addToCart(cartItem);
  };

  return (
    <div className="mt-12 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-8">
      {filteredProducts.map((product: any) => (
        <div key={product.id_destino} className="flex flex-col gap-4">
          <Link href={`/product/${product.id_destino}`}>
            <div className="relative w-full h-64 rounded-lg overflow-hidden">
              <div className="absolute inset-0 bg-gradient-to-r from-blue-100/80 to-purple-100/80" />
              {product.imagenUrl && (
                <img
                  src={product.imagenUrl}
                  alt={product.nombre}
                  className="absolute inset-0 w-full h-full object-cover"
                />
              )}
              <div className="absolute inset-0 bg-gradient-to-t from-black/50 to-transparent" />
              <div className="absolute bottom-4 left-4 right-4">
                <h3 className="text-white font-medium text-lg leading-tight">
                  {product.nombre}
                </h3>
              </div>
            </div>
          </Link>

          <div className="flex justify-between items-start">
            <h3 className="font-medium text-gray-800">
              {product.nombre}
            </h3>
            <span className="font-semibold text-pink-500">
              ${product.precio?.toLocaleString('es-AR')}
            </span>
          </div>

          <p className="text-sm text-gray-600 line-clamp-2">
            {product.descripcion}
          </p>

          <div className="flex flex-col gap-1 text-xs text-gray-500">
            <div>Ubicación: {product.ubicacion}</div>
            <div className="flex justify-between">
              <span>Máximo: {formatMaxPeople(product.maxPeople)}</span>
              <span>{product.duration}</span>
            </div>
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
                  time: "10:00",
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
            className="w-full bg-pink-500 text-white py-2 rounded-lg hover:bg-pink-600 transition-colors"
          >
            Agregar al carrito
          </button>
        </div>
      ))}
    </div>
  );
};

export default ProductList;