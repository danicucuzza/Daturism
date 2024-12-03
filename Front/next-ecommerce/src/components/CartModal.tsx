"use client"
import { useCartStore } from "../hooks/useCartStore";
import { useRouter } from 'next/navigation';
import { useState } from 'react';
const CartModal: React.FC = () => {
  const items = useCartStore((state) => state.items);
  const removeFromCart = useCartStore((state) => state.removeFromCart);
  const clearCart = useCartStore((state) => state.clearCart);
  const [isCartOpen, setIsCartOpen] = useState(false);
  const router = useRouter();
  const total = items.reduce((acc, item) => acc + item.price, 0);
  if (items.length === 0) {
    return (
      <div className="absolute top-12 right-0 bg-white p-4 rounded-md shadow-[0_3px_10px_rgb(0,0,0,0.2)] z-20">
        <p>Tu carrito está vacío</p>
      </div>
    );
  }
  const handleFinalizarCompra = () => {
    setIsCartOpen(false);
    document.body.click();
    router.push('/checkout');
  };
  return (
    <div className="absolute top-12 right-0 bg-white p-4 rounded-md shadow-[0_3px_10px_rgb(0,0,0,0.2)] z-20 min-w-[300px]">
      {items.map((item) => (
        <div key={item.id} className="flex justify-between items-start mb-4 pb-4 border-b">
          <div>
            <h3 className="font-medium">{item.name}</h3>
            <p className="text-sm text-gray-600">1 x ${item.price}</p>
            <p className="text-sm text-gray-500">{item.customizations.people} personas</p>
          </div>
          <button
            className="ml-auto text-xs text-red-500"
            onClick={() => removeFromCart(item.id)}
          >
            Eliminar
          </button>
        </div>
      ))}
      <div className="mt-4">
        <p className="font-bold mb-4">Total: ${total}</p>
        <div className="flex gap-2">
          <button
            onClick={clearCart}
            className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
          >
            Vaciar carrito
          </button>
          <button
            onClick={handleFinalizarCompra}
            className="px-4 py-2 bg-pink-500 text-white rounded hover:bg-pink-600"
          >
            Finalizar compra
          </button>
        </div>
      </div>
    </div>
  );
};
export default CartModal;