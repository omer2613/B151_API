package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.convertJsonToString;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Get13_ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {

    @Test
    public void get13() {

        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        String body = convertJsonToString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedData=convertJsonToJava(body, JsonPlaceHolderPojo.class);
        System.out.println(expectedData);

        //Send the request and get the response
        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPlaceHolderPojo actualData=convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());

    }
}
