package org.spotifyautomation.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.spotifyautomation.api.Route.BASE_PATH;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL)
                .build();

    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }
}
