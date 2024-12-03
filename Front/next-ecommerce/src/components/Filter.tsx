"use client";

import { useEffect, useState } from 'react';
import { useProducts } from '@/context/ApiContext';

const Filter = () => {
  const { products } = useProducts();
  const [categories, setCategories] = useState<string[]>([]);

  useEffect(() => {
    try {
      if (products && products.length > 0) {
        // Extraer categorías únicas de los productos
        const uniqueCategories = [...new Set(products.map(product => product.categoria))];
        setCategories(uniqueCategories);
      }
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  }, [products]);

  return (
    <div className="flex gap-4 items-center">
      <select 
        className="border rounded-lg px-3 py-2"
        defaultValue="Todas las categorías"
      >
        <option value="Todas las categorías">Todas las categorías</option>
        {categories.map((category, index) => (
          <option key={index} value={category}>
            {category}
          </option>
        ))}
      </select>
    </div>
  );
};

export default Filter;