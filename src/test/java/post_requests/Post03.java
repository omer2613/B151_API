package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends JsonPlaceHolderBaseUrl {
    /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
    When
        I send POST Request to the Url
    Then
        Status code is 201
    And
        response body is like
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false,
            "id": 201
        }
     */

    @Test
    public void post03() {
        //pojo class variable lari private yapip getter, setter methodlariyla cagisirip degisiklik yaptigimiz class lardir.

        //1.Set the URL
        spec.pathParam("first","todos");

        //2.set the expected data

        JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(55,"Tidy your room",false);

        Response response=given(spec).body(expectedData).when().post("{first}");

        response.prettyPrint();

        //Do assertion

        JsonPlaceHolderPojo acutualData=response.as(JsonPlaceHolderPojo.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),acutualData.getUserId());
        assertEquals(expectedData.getTitle(),acutualData.getTitle());
        assertEquals(expectedData.getCompleted(),acutualData.getCompleted());
    }
}
//UI Testi----> API Testi ------> Database Testi
//Postman'de API testi yapabiliriz ancak UI ve DataBase Testlerini yapamayiz.
//Bir veri gonderiyoruz, register islemi yapiyoruz.
// Bunu UI'da yaptik ve bilgileri intellij'de bir pojo class'ta kaydedebiliriz
//Bu kayitli bilgileri UI ile DataBase Testleri ile kontrol edebiliriz.
// UI, DataBase ve API testlerini yapabildigimizde full stack Otomation Engineer olabiliriz. Bu testleri intellij'de yapabiliriz.