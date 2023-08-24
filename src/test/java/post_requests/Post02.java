package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Post02 extends HerokuAppBaseUrl {
  /*
  Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like
            {
                "bookingid": 5315,
                "booking": {
                    "firstname": "John",
                    "lastname": "Doe",
                    "totalprice": 11111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-09-09",
                        "checkout": "2021-09-21"
                    }
                }
             }
   */

    @Test
    public void post() {
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the Expected Data
        HerokuAppTestData obj = new HerokuAppTestData();
        Map<String,String> bookingDatesData = obj.bookingDateMapper("2021-09-09","2021-09-21");

        Map<String,Object> expectedData = obj.expectedDataMapper("John","Doe",11111,true,bookingDatesData,null);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).when().post("{first}"); // Burda Base Url e set content type i ekledik. Post isleminde hangi veri turu ile gonderigimizi belirtmezsek Internal Server Error hatasi aliriz
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingDatesData.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesData.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
    }


    }

