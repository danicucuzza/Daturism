"use client";
import React, {useRef, useEffect, useState } from "react";

const LAND_BOT_URL = "https://storage.googleapis.com/landbot.online/v3/H-2702954-2X7GT6TZIG8SA3D1/index.json";

export default function MyLandbot() {
  const [isOpen, setIsOpen] = useState(false);
  let myLandbot = useRef<any>(null);

  useEffect(() => {
    const initLandbot = () => {
      if (!myLandbot.current) {
        const script = document.createElement("script");
        script.type = "text/javascript";
        script.async = true;
        script.setAttribute('SameSite', 'None');
        script.setAttribute('Secure', '');
        script.addEventListener("load", () => {
          myLandbot.current = new window.Landbot.Container({
            container: '#myLandbot',
            configUrl: LAND_BOT_URL,
          });
        });
        script.src = "https://cdn.landbot.io/landbot-3/landbot-3.0.0.js";
        document.body.appendChild(script);
      }
    };

    initLandbot();

    return () => {
      const script = document.querySelector('script[src*="landbot-3.0.0.js"]');
      if (script) {
        script.remove();
      }
    };
  }, []);

  return (
    <>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="fixed bottom-5 right-5 z-50 bg-blue-500 text-white p-3 rounded-full shadow-lg"
      >
        {isOpen ? "Cerrar" : "Chat"}
      </button>

      <div
        className={`fixed bottom-20 right-5 w-96 bg-white border border-gray-300 rounded-lg shadow-lg transition-all duration-300 z-40 ${
          isOpen ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-full pointer-events-none'
        }`}
        style={{ maxHeight: '600px' }}
      >
        <div className="p-3 border-b border-gray-200">
          <h2 
            className="font-semibold">Chat</h2>
        </div>
        <div id="myLandbot" style={{ height: '500px', width: '100%' }} />
      </div>
    </>
  );
}
