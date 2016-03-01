package asseco.CBP.tests;

import asseco.CBP.pages.Payments;
import com.orasi.utils.date.DateTimeConversion;
import com.orasi.utils.date.SimpleDate;
import org.testng.annotations.Test;

import java.io.IOException;


public class PaymentsActiveOneSession extends WebDriverTestBase {

    private SimpleDate data = new SimpleDate();

    @Test(enabled = true)
    public void testPrzelewZwyklyOdroczony() throws IOException {
        data.advanceDay(1);
        String dataS = data.toString("dd.MM.yyyy");
        System.out.println(dataS);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewZwykly("Automat1", "06 1130 0010 0000 0003 1620 0001", "22,22", "TYTUŁ", dataS, false);
    }

    @Test(enabled = false)
    public void testPrzelewWlasnyOdroczony() throws IOException {
        data.advanceDay(1);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewWlasny("14", "62", "11.33", "tytul", null, false);
    }

    @Test(enabled = false)
    public void testPrzelewUSOdroczony(){
        data.advanceDay(1);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewUS("Augustów", "Urząd Skarbowy", "1 - CIT", "CIT","Kwartał (K)","1","2015","NIP","1111111111","58","tst","11",null);
    }

    @Test(enabled = false)
    public void testPrzelewZUSOdroczony(){
        data.advanceDay(1);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewZUS("83 1010 1023 0000 2613 9510 0000","Opłata dodatkowa za błędy płatnika - A","012015","01","1111111111","PESEL (P)","83062417395","58","01","22",null);
    }


}
