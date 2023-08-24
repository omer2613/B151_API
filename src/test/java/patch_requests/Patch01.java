package patch_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
        1) https://jsonplaceholder.typicode.com/todos/198
        2) {
              "title": "Wash the dishes"
           }
    When
      I send PATCH Request to the Url
    Then
          Status code is 200
          And response body is like
              {
                "userId": 10,
                "title": "Wash the dishes",
                "completed": true,
                "id": 198
              }
     */

    @Test
    public void patch01() {
        // Set the URL
        spec.pathParams("first", "todos", "second", 198);

        // Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> payLoad = obj.expectedDataMethod(null, "Wash the dishes", null);
        //payLoad veri tabanina gonderdigimiz data
        Map<String, Object> expectedData = obj.expectedDataMethod(10, "Wash the dishes", true);
        //expectedData given ile veritabanina gitmez. Cunku biz payLoad ile verileri gonder demis olduk. Verilerimiz
        //expectedData icinde kaldi

        // Send the request and get the response
        Response response = given(spec).body(payLoad).when().patch("{first}/{second}");
        response.prettyPrint();
        //body icine payLoad yazdik expectedData
        //yazmadik. Boylece payLoad icine girdigimiz datalar gidecek. Patch ile gonderme yaptik. Sadece title degismis olacak
        //diger degerler null oldugundan ve biz diger class icinde null'a esit degilse veriyi ekle dedigimizden dolayi sadece
        //null olmayan title degeri degisecek


        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }

}
