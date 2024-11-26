package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Paquete;
import com.daturism.taller3.Service.ClienteService;
import com.daturism.taller3.Service.MercadoPagoService;
import com.daturism.taller3.Service.PaqueteService;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/agregaralcarrito/{id_cliente}")
    public Cliente addPaqueteInCarrito(@PathVariable Long id_cliente, @RequestBody List<Long> paqueteIds) {
        Cliente cliente = clienteService.findCliente(id_cliente);

        for (Long paqueteId : paqueteIds) {
            Paquete paquete = paqueteService.findPaquete(paqueteId);

            if (!cliente.getCarritoDeCompras().contains(paquete)) {
                cliente.getCarritoDeCompras().add(paquete); // Añade el paquete al carrito de compras del cliente
            }
        }

        clienteService.saveCliente(cliente); // Guarda el cliente con los paquetes asignados al carrito
        return cliente;
    }

    @DeleteMapping("/eliminarDelCarrito/{id_cliente}/{id_paquete}")
    public Cliente removePaqueteFromCarrito(@PathVariable Long id_cliente, @PathVariable Long id_paquete) {
        Cliente cliente = clienteService.findCliente(id_cliente);
        Paquete paquete = paqueteService.findPaquete(id_paquete);

        if (cliente.getCarritoDeCompras().contains(paquete)) {
            cliente.getCarritoDeCompras().remove(paquete);
            clienteService.saveCliente(cliente);
        }

        return cliente;
    }

    @PostMapping("/comprarCarrito/{id_cliente}")
    public RedirectView comprarCarrito(@PathVariable Long id_cliente) throws Exception {
        Cliente cliente = clienteService.findCliente(id_cliente);
        List<Paquete> carrito = cliente.getCarritoDeCompras();

        // Proceder con la lógica de compra
        // En este caso, simplemente limpiamos el carrito después de generar la preferencia de pago

        // Generar la preferencia de pago utilizando el servicio de MercadoPago
        Preference preference = mercadoPagoService.createPreference(carrito);

        // Limpiar el carrito después de generar la preferencia
        carrito.clear();

        // Guardar el cliente actualizado
        clienteService.saveCliente(cliente);

        // Redirigir al usuario a la URL generada por Mercado Pago
        return new RedirectView(preference.getInitPoint());
    }
}
