package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {
    /*
NOTLAR:
    1: Manuel testler için Postman kullanacağız.
    2:API otomasyon testleri için REST Assured kütüphanesini kullanacağız.
    3:Otomasyon kodlarını yazarken şu adımları takip edeceğiz:
        a.Gereksinimleri anlama
        b.Test senaryolarını yazma
            i.Test senaryolarını yazarken Gherkin dilini kullanacağız.
                -Given:Ön koşullar:Endpoint,body...
                -When:İşlemler:Get,Post,Put,Delete...
                -Then:Dönütler:Assertion,Close ...
                -And:Çoklu işlemlerin yapılacağı zaman kullanılır.
        c. Otomasyon kodlarını yazarken şu adımları takip ederiz:
          i. Set the URL
          ii. Set the expected data
          iii. Send the request and get the response
          iv. Do assertion

 */
    public static void main(String[] args) {
        String url="https://petstore.swagger.io/v2/pet/3435";
        Response response=given().when().get(url);
        response.prettyPrint();//response dan gelen cevabi json olarak yazdirmaya yarar
        response.statusCode();

        //Status code nasil yazilir
        System.out.println("Status Code: " + response.statusCode());

        //Content type nasil yazdirilir
        System.out.println("Content Type: " +response.contentType());

        //Status Line nasil yazdirilir
        System.out.println("Status Line :" + response.statusLine());

        //Header bolumundeki bir veri nasil yazdirilir
        System.out.println("Header | Server: " +response.header("Server"));

        //Headers bolumu nasil yazdirilir
        System.out.println("Headers :" +response.headers());

        //Time bilgisi nasil yazdirilir
        System.out.println("Time :" +response.time());

    }
}
