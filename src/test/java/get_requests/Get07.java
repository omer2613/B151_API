package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
        Given
           https://jsonplaceholder.typicode.com/todos
        When
           Kullanıcı URL'e bir GET request gönderir
        Then
           1) Status code 200 olmalı
           2) "Id"leri 190 dan büyük olanları konsola yazdırın
              "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
           3) "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
              "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
           4) "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
              "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
     */

    @Test
    public void get07() {
        //Groovy language uniq degerlerle(id, email..) bir tabloda bir kisinin bilgilerine ulasabiliriz.
        spec.pathParam("first","todos");

        Response response=given(spec).when().get("{first}");
        response.prettyPrint();

        assertEquals(200,response.statusCode());
        JsonPath json=response.jsonPath();//response'daki veriyi jsonpath ile kullanilir hale getiriyoruz
        List<Object> idList = json.getList("findAll{it.id>190}");
        System.out.println("ID List: " + idList);

        //"Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
        assertEquals(10, idList.size());

        // "Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
        List<Integer> userIdList=json.getList("findAll{it.id<5}.userId");
        System.out.println("userId List : " + userIdList);

        // "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
        assertEquals(4,userIdList.size());

        // "Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
        List<String> titleList=json.getList("findAll{it.id<5}.title");
        System.out.println("Title List : " + titleList);

        // "delectus aut autem" başlığının, id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın
       assertTrue(titleList.contains("delectus aut autem"));

       json.getList("findAll{it.title=='delectus aut autem'}.it");





    }
}
