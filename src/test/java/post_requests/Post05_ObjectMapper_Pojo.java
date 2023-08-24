package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05_ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

     /*
        Given
            1) https://jsonplaceholder.typicode.com/todos
            2) {
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
    public void post05() {
        //Set the URL
        spec.pathParam("first","todos");

        //set the expected data
        JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println(expectedData);
       Response response= given(spec).body(expectedData).when().post("{first}");
       response.prettyPrint();

       //Do assertion

        JsonPlaceHolderPojo actualdata=ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getUserId(),actualdata.getUserId());
        assertEquals(expectedData.getTitle(),actualdata.getTitle());
        assertEquals(expectedData.getCompleted(),actualdata.getCompleted());


    }
}
