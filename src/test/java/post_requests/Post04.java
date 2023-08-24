package post_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04 extends HerokuAppBaseUrl {

     /*
        Given
          1)  https://restful-booker.herokuapp.com/booking
          2) {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 },
                 "additionalneeds": "Breakfast"
              }
        When
            I send POST Request to the URL
        Then
            Status code is 200
        And
            Response body is like
                 {
                    "bookingid": 16,
                    "booking" :{
                        "firstname": "Ali",
                        "lastname": "Can",
                        "totalprice": 999,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2021-09-21",
                            "checkout": "2021-12-21"
                        },
                        "additionalneeds": "Breakfast"
                     }
                  }
     */

    @Test
    public void post04() {
        //set the url
        spec.pathParam("first","booking");
        //set the expected data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo expectedData=new BookingPojo( "Ali","Can",999,true,bookingDates, "Breakfast");
        System.out.println("expectedData = " + expectedData);
        //send the request and the response
        Response response= given(spec).body(expectedData).when().post("{first}");
        response.prettyPrint();

        //do assertion
        BookingResponsePojo actualdata=response.as(BookingResponsePojo.class);
        System.out.println(actualdata);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualdata.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualdata.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualdata.getBooking().getTotalprice());
        assertEquals(bookingDates.getCheckin(),actualdata.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualdata.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualdata.getBooking().getAdditionalneeds());


        //NOT
        /* Response'da kac scope varsa o kadar pojo olmali. Burada 3 tane vardi.
        /BookingResponsePojo'yu istedigimiz sirada olusturabiliriz.
         Ancak ona expected data kisminda yer yok. En buyuk olan hepsini kapsayan pojoyu
        Do assertion icine yaziyoruz.

         */

        //NOT
        /*
        response as methodu sadece json ve java datalari arasinda donusum yapmaz.
        jackson-databind ve jackson.datatype dependency leri ile birlikte
        response as() methodu Integer variable i String'e, String'i Local Date'e
        gibi farkli classlari birbirine cevirebilmemize imkan tanir
         */

    }
    }

