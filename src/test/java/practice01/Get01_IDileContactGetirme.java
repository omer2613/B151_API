package practice01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get01_IDileContactGetirme {
    @Test
    public void get01() {
        RestAssured.baseURI="https://thinking-tester-contact-list.herokuapp.com";
        RestAssured.basePath="/contacts/64d8079a36c2810013fe7dbb";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NGQ3YzQ1ZjlhNjk0ZTAwMTMwZjFmYzQiLCJpYXQiOjE2OTE4NzkyNDR9.CeG-bVp1kA8aMKmVRYtNgrBP8vVAZZjNJvSbpwmVfcY";

        Response response=
        given()
                .auth().oauth2(token)
                .when()
                .get();
        response.prettyPrint();

        response
                .then()
                .body("firstName", equalTo("John"))
                .body("lastName", equalToIgnoringCase("Doe"))
                .body("email", not(equalTo("jdoe@fake.com")))
                .body("email", containsString("@fake.com"))
                .body("city", startsWith("Any"))
                .body("city", endsWith("own"))
                .body("stateProvince", anyOf(equalTo("KS"), equalTo("CA")))
                .body("country", allOf(equalTo("USA"), equalToIgnoringCase("usa")))
                .body("__v", greaterThan(-1));



    }
}
