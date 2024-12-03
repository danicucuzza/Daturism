"use client";
import { useRouter } from 'next/navigation';

const CategoryList = () => {
  const router = useRouter();
  
  const categories = [
    {
      id: 'day',
      title: "Día",
      description: "Experiencias para disfrutar bajo el sol",
      image: "https://images.pexels.com/photos/13278009/pexels-photo-13278009.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    },
    {
      id: 'night',
      title: "Noche",
      description: "Experiencias para vivir después del atardecer",
      image: "https://images.pexels.com/photos/20897698/pexels-photo-20897698/free-photo-of-ciudad-punto-de-referencia-noche-rascacielos.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    }
  ];

  return (
    <div className="flex gap-8 overflow-x-auto pb-8 px-4 md:px-8 lg:px-16 xl:px-32 2xl:px-64">
      {categories.map((category) => (
        <div 
          key={category.id}
          onClick={() => router.push(`/list?category=${category.id}`)}
          className="min-w-[300px] h-[200px] relative rounded-lg overflow-hidden cursor-pointer group"
        >
          <img
            src={category.image}
            alt={category.title}
            className="absolute inset-0 w-full h-full object-cover"
          />
          <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-black/20 group-hover:from-black/80 transition-all duration-300" />
          <div className="absolute bottom-4 left-4 right-4 text-white">
            <h3 className="text-xl font-medium mb-2">{category.title}</h3>
            <p className="text-sm text-gray-200">{category.description}</p>
          </div>
        </div>
      ))}
    </div>
  );
};

export default CategoryList;