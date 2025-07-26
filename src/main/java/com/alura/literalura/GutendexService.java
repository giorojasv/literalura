package com.alura.literalura;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class GutendexService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<LibroDTO> buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        ResponseEntity<GutendexResponse> response = restTemplate.getForEntity(url, GutendexResponse.class);
        if (response.getBody() != null && !response.getBody().getResults().isEmpty()) {
            return Optional.of(response.getBody().getResults().get(0));
        }
        return Optional.empty();
    }
}