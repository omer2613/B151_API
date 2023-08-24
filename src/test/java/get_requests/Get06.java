package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerokuAppBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking/22
    When
    User send a GET request to the URL
    Then
    HTTP Status Code should be 200
    And
    Response content type is “application/json”
    And
    Response body should be like;
    {
        "firstname": "John",
            "lastname": "Smith",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
        "checkin": "2018-01-01",
                "checkout": "2019-01-01"
    },
        "additionalneeds": "Breakfast"
    }
*/

    @Test
    public void Get() {
        //1. Set Url
        spec.pathParams("first","booking","second",222);

        //2. Set Expected data
        //3. Set request and get response
        Response response = given().spec(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //4. Do assertion

        // 1. yol
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("John")
                        ,"lastname",equalTo("Smith")
                        ,"totalprice",equalTo(111)
                        ,"depositpaid",equalTo(true)
                        ,"bookingdates.checkin", equalTo("2018-01-01")
                        ,"bookingdates.checkout", equalTo("2019-01-01")
                        ,"additionalneeds", equalTo("Breakfast"));

        //bookingdates'ten sonra . koyup bookingdates body sine girerek checkin  degerini kontrol edebiliriz

        // 2. yol
        //json da datamizi String'e Int'e List'e vs. cevirip ilerde get islemlerinde ve dogrulama yaparken kullanabiliriz
        JsonPath json = response.jsonPath(); // response jsonPath() metodu kullnarak jsonPath data tipine dönüştürdük
                                            //jsonPath datasından response da datalara kolayca ulaşabiliriz.
        System.out.println(json.getString("firstname")); // jsonPath() sayesinde istediğimiz datayı getirebiliriz

        assertEquals("John", json.getString("firstname"));
        assertEquals("Smith", json.getString("lastname"));
        assertEquals(111, json.getInt("totalprice"));
        assertTrue (json.getBoolean("depositpaid"));
        assertEquals("2018-01-01", json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", json.getString("bookingdates.checkout"));
        assertEquals("Breakfast", json.getString("additionalneeds"));

        //3.YOL SOFT ASSERTİON//Soft asserts TestNg ile gelen bir ozellik oldugu icin TestNg pom'a yuklenmeli

        SoftAssert softAssert = new SoftAssert();
        ResourceBundle jsonPath;
        softAssert.assertEquals(json.getString("firstname"),"John");
        softAssert.assertEquals(json.getString("lastname"),"Smith");
        softAssert.assertEquals(json.getInt("totalprice"),111);
        softAssert.assertTrue(json.getBoolean("depositpaid"));
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");
        softAssert.assertEquals(json.getString("additionalneeds"),"Breakfast");
        softAssert.assertAll();




    }
}
