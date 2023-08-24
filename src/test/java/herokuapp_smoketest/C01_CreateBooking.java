package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreateBooking extends HerokuAppBaseUrl {
    /*
    Given
    https://restful-booker.herokuapp.com/booking
   And
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"

    When
    Kullanaci post request gönderir

    Then
    Status code=200

    And
    {
    "bookingid": 1,
    "booking": {
        "firstname": "Jim",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
    }
}


}

     */

    public static int bookingid;
    @Test
    public void newPost01() {
        spec.pathParam("first","booking");

        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");


       Response response= given(spec).body(expectedData).when().post("{first}");
       response.prettyPrint();
       bookingid =response.jsonPath().getInt("bookingid");
       System.out.println(bookingid);


       BookingResponsePojo actualData=convertJsonToJava(response.asString(), BookingResponsePojo.class);

       assertEquals(200,response.statusCode());
       assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
       assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
       assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
       assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
       assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
       assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
       assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


    }
}
