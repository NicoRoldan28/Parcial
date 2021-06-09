package com.utn.parcial.api;

import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiCallServiceDolar {
    @CircuitBreaker(name = "Dolar", fallbackMethod = "fallback")
    public EuroResponse getDolar() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.dolarsi.com/api/api.php?type=dolar"))
                .header("accept", "application/json")
                .header("x-rapidapi-key", "29f859f1f8msh9dd378a194d5945p1b8c68jsnec2e318dc67a")
                .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), EuroResponse.class);
    }


    public EuroResponse fallback(final Throwable t) {
        log.info("Fallback cause, {}", t.toString());
        return EuroResponse.builder().build();
    }
}
