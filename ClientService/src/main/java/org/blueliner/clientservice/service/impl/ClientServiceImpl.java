package org.blueliner.clientservice.service.impl;

import org.blueliner.clientservice.dto.CreateOrderRequest;
import org.blueliner.clientservice.dto.OrderRequest;
import org.blueliner.clientservice.dto.OrderResponse;
import org.blueliner.clientservice.dto.UpdateOrderStatusRequest;
import org.blueliner.clientservice.exception.type.ClientNotFoundException;
import org.blueliner.clientservice.model.Client;
import org.blueliner.clientservice.repository.ClientRepository;
import org.blueliner.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String CLIENT_NOT_FOUND = "Client not found";
    @Value("${client.service.url}")
    private String ORDER_SERVICE_URL;
    private final ClientRepository clientRepository;
    private final RestTemplate restTemplate;

    public ClientServiceImpl(ClientRepository clientRepository, RestTemplate restTemplate) {
        this.clientRepository = clientRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<OrderResponse> createOrder(CreateOrderRequest createOrderRequest) {
        Long clientId = getClientId(createOrderRequest.email());
        return restTemplate.postForEntity(ORDER_SERVICE_URL,
                OrderRequest.builder()
                        .clientId(clientId)
                        .description(createOrderRequest.description())
                        .build(),
                OrderResponse.class);
    }

    @Override
    public ResponseEntity<OrderResponse> getOrder(Long clientId, Long orderId) {
        isClientExist(clientId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("clientId", String.valueOf(clientId));
        return restTemplate.exchange(ORDER_SERVICE_URL + "/" + orderId,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                OrderResponse.class);
    }

    @Override
    public ResponseEntity<OrderResponse> changeOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        return restTemplate.exchange(ORDER_SERVICE_URL + "/status",
                HttpMethod.PUT,
                new HttpEntity<>(updateOrderStatusRequest),
                OrderResponse.class);
    }


    private Long getClientId(String email) {
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.isPresent() ?
                client.get().getId() :
                clientRepository.save(Client.builder().email(email).build()).getId();
    }

    private void isClientExist(Long clientId) {
        clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(CLIENT_NOT_FOUND));
    }

}
