package get_requests;
import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
public class Get05 extends HerokuAppBaseUrl {
    /*
    Given
     https://restful-booker.herokuapp.com/booking
When
     User send a request to the URL
Then
     Status code is 200
And
     Among the data there should be someone whose firstname is "Sally" and last name is "Brown"
     */
    @Test
    public void Get(){
        //1. Set Url
        spec.pathParam("first","booking")
                .queryParams("firstname","Sally"
                        ,"lastname","Brown");
        //2. Set Expected data
        //3.Sent req. get resp
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        //4. Do assertion
        response.then().statusCode(200)
                .body(containsString("bookingid"))
                .body("bookingid",hasSize(greaterThan(0)));
        assertTrue(response.asString().contains("bookingid"));
    }
}
