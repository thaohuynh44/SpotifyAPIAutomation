package org.spotifyautomation.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.spotifyautomation.utils.ConfigLoader;

import java.time.Instant;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.spotifyautomation.api.Route.*;
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
        formParams.put("client_id", ConfigLoader.getInstance().getProperty("client_id"));
        formParams.put("client_secret", ConfigLoader.getInstance().getProperty("client_secret"));
        formParams.put("refresh_token", ConfigLoader.getInstance().getProperty("refresh_token"));
        formParams.put("grant_type", "refresh_token");

        Response response = given(getAccountRequestSpec())
            .formParams(formParams).
        when()
                .post(API + TOKEN).
        then()
                .spec(getResponseSpec())
                .extract()
                .response();

        return response;
    }
}
