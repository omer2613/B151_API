package herokuapp_smoketest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        C01_CreateBooking.class,
        C02_GetBookingById.class,
        C03_UpdateBooking.class,
        C04_PartialUpdateBooking.class,
        C05_DeleteBooking.class,
        C06_GetBookingById_NegativeTest.class


})
public class Runner {
}
/*
Bir class’ta run edince calistiramadik. Ancak Runner’da calisiyor.
Bir class’ta run yapinca console’da en alt kisimda Process finished with exit code 0 yazisi geliyor.
Bu su anlama geliyor. Artik bu asamada public static in bookingid olarak tanimladigimiz ve
 icinde bookingid’yi barindiran id artik bir daha olmuyor ve ikinci calistirmada geri gelmeyen bookingid nedeniyle testimiz calismiyor.
  Runner’da ise bookingid tum testler calisincaya kadar duruyor.
Runner’da classlar sirasiyla calisirken aralarina Process finished with exit code 0 yazisi cikmiyor.
En sonda tum classlar calistiktan sonra cikiyor. Bu nedenle Runner’da classlar sorunsuz calisiyor.
 */
