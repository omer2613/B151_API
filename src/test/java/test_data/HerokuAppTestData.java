package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuAppTestData {
    public Map<String, String> bookingDateMapper(String checkin, String checkout){
        Map<String, String> bookingdatesData = new HashMap<>();
        if (checkin!=null){
            bookingdatesData.put("checkin", checkin);
        }
        if (checkout!=null){
            bookingdatesData.put("checkout", checkout);
        }
        return bookingdatesData;
    }

    public Map<String, Object> expectedDataMapper(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates, String additionalneeds){
        Map<String, Object> expectedData = new HashMap<>();
        if (firstname!=null){
            expectedData.put("firstname", firstname);
        }
        if (lastname!=null){
            expectedData.put("lastname", lastname);
        }
        if (totalprice!=null){
            expectedData.put("totalprice", totalprice);
        }
        if (depositpaid!=null){
            expectedData.put("depositpaid", depositpaid);
        }
        if (bookingdates!=null){
            expectedData.put("bookingdates", bookingdates);
        }
        if (additionalneeds!=null){
            expectedData.put("additionalneeds", additionalneeds);
        }
        return expectedData;
    }
}

    /*
    // Set the expected data
        Map<String, String> bookingdatesData = new HashMap<>();//header, status code, time, size bilgileri response'un icerisinde mevcut. actualData ile sadece body icesindeki datalari test ettik
        bookingdatesData.put("checkin", "2018-01-01");
        bookingdatesData.put("checkout", "2019-01-01");
        System.out.println("BookingDates Data: " + bookingdatesData);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Jane");
        expectedData.put("lastname", "Doe");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesData);//Ic ice datalarla islem yaparken once icerden islem yapmaya baslariz.
        expectedData.put("additionalneeds", "Extra pillows please");
        System.out.println("Expected Data: " + expectedData);
     */

