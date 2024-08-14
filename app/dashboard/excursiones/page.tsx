import Link from 'next/link';

export default async function Page() {
  const products = [
    {
      id: 1,
      name: 'Product 1',
      descripcion: 'vas a poder conocer',
      img: 'public\md.png',
    },
    {
      id: 2,
      name: 'Product 2',
      descripcion: 'vas a poder conocer',
      img: "public\md.png",
    },
    {
      id: 3,
      name: 'Product 3',
      descripcion: 'vas a poder conocer',
      img: 'public\md.png',
    },
  ];
  return (
    <div>
      <h1>Products</h1>
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {products.map((product) => (
          <div className="max-w-sm overflow-hidden rounded bg-white shadow-lg flex-col">
            <div key={product.id}>
              <div className="h-80 w-80 bg-gray-200 p-4 text-center text-xl font-bold">
                <Link href={`excursiones/${product.id}`}>{product.name}</Link>
                <img className="w-full bg-cover bg-center" src="{product.img}" alt="excursion" />
              </div>
            </div>
            <div className="text-center text-base text-gray-700">
              <p>{product.descripcion}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
