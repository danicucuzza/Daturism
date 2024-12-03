"use client";

import { useCartStore } from '@/hooks/useCartStore';
import { useRouter } from 'next/navigation';

const Checkout = () => {
  const { items, removeFromCart, clearCart, getTotal } = useCartStore();
  const router = useRouter();

  const handleCheckout = () => {
    // Aquí puedes agregar la lógica para procesar el pago
    alert('Procesando tu compra...');
    // Después de procesar el pago exitosamente:
    // clearCart();
    // router.push('/confirmacion');
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <h1>Checkout</h1>
      <div>
        <h2>Resumen del pedido</h2>
        
        {items.map((item) => (
          <div key={item.id}>
            <div className="flex justify-between items-center">
              <div>
                <h3>{item.name}</h3>
                <p>{item.quantity} x ${item.price}</p>
                <p>{item.customizations.people} personas</p>
                <p>{item.customizations.date} - {item.customizations.time}</p>
              </div>
              <div>
                <p>${Number(item.price) * Number(item.quantity)}</p>
                <button 
                  onClick={() => removeFromCart(item.id)}
                  className="text-red-500 hover:text-red-700"
                >
                  Eliminar
                </button>
              </div>
            </div>
          </div>
        ))}
        
        <div className="mt-6">
          <div className="flex justify-between items-center">
            <p className="font-bold">Total: ${getTotal().toLocaleString('es-AR')}</p>
            <div className="space-x-4">
              <button 
                onClick={clearCart}
                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
              >
                Vaciar carrito
              </button>
              <button 
                onClick={handleCheckout}
                className="bg-green-500 text-white px-6 py-2 rounded hover:bg-green-600"
              >
                Finalizar compra
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout; 