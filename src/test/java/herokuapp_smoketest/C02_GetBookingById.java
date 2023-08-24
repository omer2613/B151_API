package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;
import static herokuapp_smoketest.C01_CreateBooking.bookingid;

public class C02_GetBookingById extends HerokuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/:id
        When
            Kullanici GET request gonderir
        Then
            Status Code = 200
        And
            Body:
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
}
     */

    @Test
    public void getBookingById() {
        spec.pathParams("first","booking","second", bookingid);

        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData=new BookingPojo("Jim","Brown",111,true,bookingDates,"Breakfast");


        Response response=given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        BookingPojo actualData=convertJsonToJava(response.asString(),BookingPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());






    }
}

