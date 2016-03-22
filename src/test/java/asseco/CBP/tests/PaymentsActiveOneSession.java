package asseco.CBP.tests;

import asseco.CBP.dataDriven.ClientData;
import asseco.CBP.pages.Payments;
import asseco.CBP.pages.PaymentsActive;
import com.orasi.utils.date.SimpleDate;
import com.orasi.utils.types.Helpers;
import org.testng.annotations.Test;

import java.io.IOException;


public class PaymentsActiveOneSession extends WebDriverTestBase {

    private SimpleDate data = new SimpleDate();
    private String kwota = Helpers.losujKwote();
    private String tytul = Helpers.zwrocUID();
    ClientData user = new ClientData();
    String nrb = user.getNrbShort();

    @Test(enabled = false)
    public void testPrzelewZwyklyOdroczony() throws IOException {
        data.advanceDay(1);
        String dataS = data.toString("dd.MM.yyyy");
        System.out.println(dataS);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewZwykly("Automat1", "06 1130 0010 0000 0003 1620 0001", kwota, tytul, dataS, false);
    }

    @Test(enabled = false)
    public void testPrzelewWlasnyOdroczony() throws IOException {
        data.advanceDay(1);
        kwota = Helpers.losujKwote();
        String dataS = data.toString("dd.MM.yyyy");
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewWlasny("14", "62", kwota, tytul, dataS, false);
    }

    @Test(enabled = false)
    public void testPrzelewUSOdroczony() {
        data.advanceDay(1);
        kwota = Helpers.losujKwote();
        String dataS = data.toString("dd.MM.yyyy");
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewUS("Augustów", "Urząd Skarbowy", "1 - CIT", "CIT", "Kwartał (K)", "1", "2015", "NIP", "1111111111", "58", "tst", kwota, dataS);
    }

    @Test(enabled = true)
    public void testPrzelewZUSOdroczony() throws InterruptedException {
        data.advanceDay(1);
        kwota = Helpers.losujKwote();
        String dataS = data.toString("dd.MM.yyyy");

        System.out.println("nrb=" + nrb);
        Payments payments = homePage.navigationMenu().navigateToPayments();
        payments.uzupelnijPrzelewZUS("83 1010 1023 0000 2613 9510 0000", "Opłata dodatkowa za błędy płatnika - A", "012015", "01", "1111111111", "PESEL (P)", "83062417395", nrb, "01", kwota, dataS);
        Thread.sleep(60000);
        PaymentsActive activePayments = homePage.navigationMenu().navigateToPaymentsActive();
        activePayments.wyszukajPlatnoscPoTekscie(kwota);
        activePayments.kliknijWAnuluj();
    }

}
