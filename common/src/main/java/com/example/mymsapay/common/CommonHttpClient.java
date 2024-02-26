package com.example.mymsapay.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
@Slf4j
public class CommonHttpClient {
    private final HttpClient httpClient;

    public CommonHttpClient() {
        httpClient = HttpClient.newBuilder().build();
    }

    public HttpResponse<String> sendGetRequest(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .timeout(Duration.ofSeconds(2))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    public HttpResponse<String> sendPostRequest(String url, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .timeout(Duration.ofSeconds(2))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}

