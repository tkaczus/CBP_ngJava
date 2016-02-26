package asseco.CBP.tests;

import asseco.CBP.pages.Payments;
import org.testng.annotations.Test;

import java.io.IOException;


public class PaymentsOneSession extends WebDriverTestBase {

    //	@Test
    public void testPrzelewZwykly() throws IOException {
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewZwykly("Automat1", "06 1130 0010 0000 0003 1620 0001", "22,22", "TYTUŁ", null, false);
    }

    @Test
    public void testPrzelewWlasny() throws IOException {
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewWlasny("14", "62", "11.33", "tytul", null, false);
    }


}
