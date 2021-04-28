package com.quarksy;

import com.quarksy.domain.Developer;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DevelopersResourceTest {

    @Test
    public void getDevelopers() {
        given()
          .when().get("/api/v1/developers/")
          .then()
//                .statusCode(200)
                .body(is("hello"));
    }

    @Test
    public void postDeveloper() {

        String requestBody = "{ \"id\": \"982f5c0b-33c9-4d17-ba3c" +
                "-d06c45f28ee1\", \"name\": \"Bev Bevington\", \"team\": " +
                "\"The B Team\", \"skills\": [\"a\", \"b\", \"c\"]," +
                "\"createdAt\": \"2020-09-20T14:34:23+10:00\", \"updatedAt\":" +
                " \"2020-09-20T14:34:23+10:00\" }";

        given().body(requestBody)
                .when().post("/api/v1/developers/")
                .then()
                .statusCode(200);
//                .body(is("Hello RESTEasy"));
    }

    @Test
    public void patchDevelopers() {

        String requestBody = "{ \"id\": \"982f5c0b-33c9-4d17-ba3c" +
                "-d06c45f28ee1\", \"name\": \"Bev Bevington\", \"team\": " +
                "\"The B Team\", \"skills\": [\"a\", \"b\", \"c\"]," +
                "\"createdAt\": \"2020-09-20T14:34:23+10:00\", \"updatedAt\":" +
                " \"2020-09-20T14:34:23+10:00\" }";

        given().body(requestBody)
                .when().patch("/developers/982f5c0b-33c9-4d17-ba3c-d06c45f28ee1")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }


}