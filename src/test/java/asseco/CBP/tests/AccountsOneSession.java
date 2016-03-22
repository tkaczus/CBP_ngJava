package asseco.CBP.tests;

import asseco.CBP.dataDriven.ClientData;
import asseco.CBP.pages.Accounts;
import com.orasi.utils.date.SimpleDate;
import com.orasi.utils.types.Helpers;
import org.testng.annotations.Test;

import java.io.IOException;

public class AccountsOneSession extends WebDriverTestBase {

    private SimpleDate data = new SimpleDate();
    private String kwota = Helpers.losujKwote();
    private String tytul = Helpers.zwrocUID();
    ClientData user = new ClientData();
    String nrb = user.getNrbLong();
    String dostepneSrodki = user.getAwaibleFromNRBLong(nrb);

    @Test
    public void sprawdzSzczegolyRachunku() throws IOException {
        Accounts rachunki = homePage.navigationMenu().navigateToAccounts();
        rachunki.wyszukajRachunek(nrb);
        rachunki.wybierzSzczegolyRachunku(nrb,dostepneSrodki);
    }


}
