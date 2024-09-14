package org.spotifyautomation.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.spotifyautomation.api.SpecBuilder.getAccountRequestSpec;
import static org.spotifyautomation.api.SpecBuilder.getResponseSpec;

public class TokenManager {
    private static String accessToken;
    private static Instant expiryTime;

    public static String getToken() {
        try {
            if (accessToken == null || Instant.now().isAfter(expiryTime)) {
                System.out.println("Renewing token ...");
                Response response = renewToken();
                accessToken = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("ABORT! Failed to get token");
        }

        return accessToken;
    }

    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", "f16c4541d4c146b4a160b1496a5a5434");
        formParams.put("client_secret", "fca34a1237f447f7affdca7dc1b34630");
        formParams.put("refresh_token", "AQC-5C65yZwvwWaDRktFY5zKj_hAxjVFO8hBSez6LlTy2tkea3P0GQHxH8c9t_fMEp0glzo_DqSieBc6UsEC29kTIcyodt1YnD75rEHz1Xv6XMc1jiLhEZhPwRnsgV8LAQs");
        formParams.put("grant_type", "refresh_token");

        Response response = given(getAccountRequestSpec())
            .formParams(formParams).
        when()
                .post("/api/token").
        then()
                .spec(getResponseSpec())
                .extract()
                .response();

        return response;
    }
}
