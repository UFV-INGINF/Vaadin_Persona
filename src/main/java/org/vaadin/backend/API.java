package org.vaadin.backend;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class API {

    private static final String urlPrefix = "https://swapi.dev/api/people/%s";

    public String getPerson(int id) throws URISyntaxException, IOException, InterruptedException {
        String fullUrl = String.format(urlPrefix, id);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(fullUrl))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }
}
