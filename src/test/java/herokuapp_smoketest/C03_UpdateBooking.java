package herokuapp_smoketest;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;

import static herokuapp_smoketest.C01_CreateBooking.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C03_UpdateBooking extends HerokuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Kullanıcı PUT request gönderir
    Then
        Status Code = 200
    And
        Body:
            {
                "firstname" : "James",
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
    public void updateBooking() {

        spec.pathParams("first","booking","second", bookingid);

        BookingDatesPojo bookingDates=new BookingDatesPojo("2018-01-01","2019-01-01" );
        BookingPojo expectedData=new BookingPojo("James","Brown", 111,true,bookingDates,"Breakfast");

       Response response= given(spec).body(expectedData).when().put("{first}/{second}");

       BookingPojo actualData=convertJsonToJava(response.asString(),BookingPojo.class);
        System.out.println(actualData);

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
