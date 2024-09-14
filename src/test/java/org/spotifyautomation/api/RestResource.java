package org.spotifyautomation.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.spotifyautomation.pojo.Playlist;

import static io.restassured.RestAssured.given;
import static org.spotifyautomation.api.SpecBuilder.getRequestSpec;
import static org.spotifyautomation.api.SpecBuilder.getResponseSpec;

public class RestResource {

    public static Response post(String path, String token, Object requestPayload) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(requestPayload).
        when()
                .post(path).
        then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response get(String path, String token) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token).
        when()
                .get(path).
        then()
                .spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response update(String path, String token, Object requestPaylod) {
        return given(getRequestSpec())
                .header("Authorization", "Bearer " + token)
                .body(requestPaylod).
        when()
                .put(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
}
