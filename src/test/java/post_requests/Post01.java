package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Post01 extends JsonPlaceHolderBaseUrl {
    /*
    Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
           }
        When
            Kullanıcı URL'e bir POST request gönderir
        Then
            Status code 201 olmalı
        And
            Response şu şekilde olmalı:
            {
                "userId": 55,
                "title": "Tidy your room",
                "completed": false,
                "id": 201
            }
     */

    @Test
    public void post01() {
        //1.ste the url
        spec.pathParam("first", "todos");

        //2.set the expected data
        String pyLoad = "{\n" +
                "             \"userId\": 55,\n" +
                "             \"title\": \"Tidy your room\",\n" +
                "             \"completed\": false\n" +
                "           }";

        //3.set the request and get the response
        Response response = given(spec).body(pyLoad).when().post("{first}");
        response.prettyPrint();

        //4.Do assertion
        assertEquals(201, response.statusCode());

        JsonPath json = response.jsonPath();
        assertEquals(55, json.getInt("userId"));
        assertEquals("Tidy your room", json.getString("title"));
        assertFalse(json.getBoolean("completed"));


    }

    @Test
    public void post01Map() {
        //1.ste the url
        spec.pathParam("first", "todos");

        //2.set the expected data
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 55);
        expectedData.put("title", "Tidy your room");
        expectedData.put("completed", false);
        System.out.println(expectedData);

        //3.set the request and get the response
        Response response = given(spec).body(expectedData).when().post("{first}");// Serialization = Java Map datasının Json datasına çevrilmesidir
        response.prettyPrint();

        //4.Do assertion

        Map<String,Object> actualData=response.as(HashMap.class);// De-Serialization = Json objesinin Java Map objesine çevrilmesidir.
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));


        //Biz map gonderiyoruz karsi tarafa ama bizden json bekleniyor, bu java objesini jason objesine donusturmeliyiz
        //Java object'in Json object'e cevrilmesine serialization deniyor
        //Bunu yapabilmek icin de POM.xml'e Jackson repository'sini ekleyecegiz
    }
}
   /* {
        "userId": 55,
            "title": "Tidy your room",
            "completed": false
    }*/