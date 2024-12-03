import { create } from 'zustand';
import { persist } from 'zustand/middleware';

interface CartItem {
  id: string;
  name: string;
  price: number;
  quantity: number;
  description: string;
  customizations: {
    date: string;
    time: string;
    people: number;
  };
}

interface CartStore {
  items: CartItem[];
  addToCart: (item: CartItem) => void;
  removeFromCart: (itemId: string) => void;
  clearCart: () => void;
  getTotal: () => number;
}

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

const getDefaultTime = (productName: string) => {
  if (nightProducts.includes(productName)) {
    return "19:00";
  }
  return "10:00";
};

export const useCartStore = create<CartStore>()(
  persist(
    (set, get) => ({
      items: [],
      
      addToCart: (item) => set((state) => ({
        items: [...state.items, { 
          ...item, 
          price: Number(item.price),
          customizations: {
            ...item.customizations,
            time: getDefaultTime(item.name)
          }
        }]
      })),
      
      removeFromCart: (itemId) => set((state) => ({
        items: state.items.filter((item) => item.id !== itemId)
      })),
      
      clearCart: () => set({ items: [] }),
      
      getTotal: () => {
        const state = get();
        return state.items.reduce((total, item) => {
          return total + (Number(item.price) * Number(item.quantity));
        }, 0);
      },
    }),
    {
      name: 'cart-storage'
    }
  )
);