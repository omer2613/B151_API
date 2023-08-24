package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationHerokuApp {

    public static String generateToken(){
        String body="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        Response response=given().body(body).contentType(ContentType.JSON).when().post("https://restful-booker.herokuapp.com/auth");

        return response.jsonPath().getString("token");
    }
}
/*
Veritabanlari her gonderilen dataya bir id numarasi verirler.
 Biz 6 data girdik ve response’da ayni datalardan baska bookingid geldi.
  Biz bu bookingid icin class seviyesinde public static int booking; tanimladik.
   bookingid’yi birden fazla islemde baska class’larda da kullandik.
Authentication icin bir testdata class’i olusturup icinde generateToken() isimli metodu olusturduk.
Boylece authentication sorununu hallettik. Authentication’I nasil yapacagimiz API dokumaninda aciklanmisti.
Cookie ile birlikte token’in Header kisminda tutulacagi aciklanmisti. Bunu yaptik.
Bizim icin en onemli is assertion yapmaktir. Request yapmamizin nedeni assertion yapmaktir.
 */