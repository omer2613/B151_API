package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class JsonPlaceHolderBaseUrl {
   protected RequestSpecification spec;

    @Before
    public void setUp()  {
        spec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").setContentType(ContentType.JSON).build();
        // Her sorguda tekrar eden dataları buraya gireceğiz



    }
}
