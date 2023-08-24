package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.C01_CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C05_DeleteBooking extends HerokuAppBaseUrl {

    /*
    Given
    https://restful-booker.herokuapp.com/booking/:id

    When
    Kullanici Request gonderir

    Then
    Staus Code=201

    And
    Body:
    Created


     */

    @Test
    public void deleteBooking() {

        spec.pathParams("first", "booking", "second", bookingid);
        /*
        Body’de Created sonucu geldi. Body Created bir String’dir. Beklenen datayi bir String’e koyariz.
String expectedData=”Created”;
Genelde isimizi kolaylastirmak icin map ve pojo kullaniyorduk.
Burada bir tane data ve bu data String olunca String’I kullanmak daha uygun olur.
         */

        String expectedData = "Created";

        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        assertEquals(201, response.statusCode());
        assertEquals(expectedData, response.asString());


    }
}
