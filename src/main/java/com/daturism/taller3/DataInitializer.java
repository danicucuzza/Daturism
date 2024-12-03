package com.daturism.taller3;

import com.daturism.taller3.Model.Administrador;
import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Service.AdministradorService;
import com.daturism.taller3.Service.ClienteService;
import com.daturism.taller3.Service.DestinoService;
import com.daturism.taller3.Service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DestinoService destinoService;
    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AdministradorService administradorService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        // Destinos predeterminados
        List<Destino> destinos = new ArrayList<>(Arrays.asList(
                new Destino("Tango en El Viejo Almacén", "Disfruta de un espectáculo clásico de tango en uno de los lugares más emblemáticos del barrio de San Telmo.", new BigDecimal("50000.00"), 4, "4 Horas", "San Telmo", "https://images.pexels.com/photos/3894247/pexels-photo-3894247.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Tour Histórico por el Casco Antiguo", "Descubre la historia y arquitectura de Buenos Aires en un recorrido guiado por San Telmo y Plaza de Mayo.", new BigDecimal("17500.00"), 2, "2 Horas", "San Telmo", "https://images.pexels.com/photos/22605358/pexels-photo-22605358/free-photo-of-ciudad-punto-de-referencia-viaje-viajar.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Show de Tango en Café de los Angelitos", "Una experiencia cultural que combina música, danza y gastronomía en un café histórico de la ciudad.", new BigDecimal("38500.00"), 3, "2 Horas", "Balvanera", "https://images.pexels.com/photos/29546546/pexels-photo-29546546/free-photo-of-elegante-disposicion-de-la-mesa-en-un-restaurante-con-poca-luz.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Navegación por el Delta del Tigre", "Explora los encantos del Delta del Tigre en un paseo en barco que incluye almuerzo regional.", new BigDecimal("36750.00"), 4, "2 Horas", "Tigre", "https://images.pexels.com/photos/8579823/pexels-photo-8579823.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Recorrido por Caminito y La Boca", "Sumérgete en el arte y la cultura del barrio de La Boca, famoso por su colorido paseo Caminito.", new BigDecimal("21000.00"), 5, "3 Horas", "La Boca", "https://images.pexels.com/photos/16150844/pexels-photo-16150844/free-photo-of-edificio-arbol-pavimento-silla.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Tour Nocturno por Buenos Aires", "Admira la ciudad iluminada y sus principales puntos turísticos como el Obelisco y Puerto Madero.", new BigDecimal("22750.00"), 4, "3 Horas", "Buenos Aires", "https://images.pexels.com/photos/14601806/pexels-photo-14601806.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Delta del Tigre con Brunch", "Navega por los ríos del Delta del Tigre mientras disfrutas de un brunch delicioso en un entorno natural único.", new BigDecimal("54250.00"),5, "4 Horas",  "Tigre", "https://images.pexels.com/photos/5038230/pexels-photo-5038230.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Parque de la Costa", "Pasa un día lleno de diversión en el parque de atracciones más grande de Argentina, con montañas rusas y juegos para toda la familia.", new BigDecimal("47250.00"), 2, "4 Horas", "Tigre", "https://images.pexels.com/photos/29205775/pexels-photo-29205775/free-photo-of-carnaval.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Paseo en Barco por Puerto Madero", "Explora la ciudad desde una perspectiva única navegando entre Puerto Madero y La Boca.", new BigDecimal("19600.00"),4, "3 Horas",  "Puerto Madero", "https://images.pexels.com/photos/5274059/pexels-photo-5274059.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Masterclass de Mate con Merienda", "Aprende a cebar el mate perfecto y acompaña la experiencia con una merienda típica argentina.", new BigDecimal("10500.00"),4, "4 Horas",  "Centro de Buenos Aires", "https://images.pexels.com/photos/8279921/pexels-photo-8279921.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("City Tour por Buenos Aires", "Recorre los lugares más emblemáticos de Buenos Aires, desde La Boca hasta Recoleta.", new BigDecimal("19600.00"), 2, "6 Horas", "Buenos Aires", "https://images.pexels.com/photos/15942567/pexels-photo-15942567/free-photo-of-ciudad-punto-de-referencia-calle-edificios.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
                new Destino("Experiencia de Asado Tradicional", "Vive el auténtico sabor argentino en un asado familiar lleno de tradición y camaradería.", new BigDecimal("35000.00"), 2, "4 Horas", "Buenos Aires", "https://images.pexels.com/photos/13249932/pexels-photo-13249932.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
        ));
        destinoService.saveAll(destinos);

//        // Admin predeterminado
//        Administrador admin = new Administrador("losadmin", "admin", "gianvago");
//        administradorService.saveAdmin(admin);
    }
}