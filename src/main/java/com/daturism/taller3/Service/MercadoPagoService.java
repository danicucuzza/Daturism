package com.daturism.taller3.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {

    @Value("${mercadopago.access.token}")
    private String accessToken;

    public Preference createPreference() throws Exception {
        MercadoPagoConfig.setAccessToken(accessToken);

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id("1234")
                .title("Paquete turistico")
                .description("5 destinos")
                .pictureUrl("https://example.com/viaje.jpg")
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(new BigDecimal("4500.00"))
                .build();

        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .backUrls(PreferenceBackUrlsRequest.builder()
                       .success("https://example.com/success")
                       .failure("https://example.com/failure")
                       .pending("https://example.com/pending")
                       .build())
                .autoReturn("approved")
                .notificationUrl("https://asdasdasd.com")
                .build();

        PreferenceClient client = new PreferenceClient();
        return client.create(preferenceRequest);
    }
}