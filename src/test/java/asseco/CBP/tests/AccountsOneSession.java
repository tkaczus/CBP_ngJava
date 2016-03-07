package asseco.CBP.tests;

import asseco.CBP.pages.Accounts;
import asseco.CBP.pages.Awizo;
import org.testng.annotations.Test;

import java.io.IOException;

public class AccountsOneSession extends WebDriverTestBase {

    @Test
    public void sprawdzSzczegolyRachunku() throws IOException {
        Accounts rachunki = homePage.navigationMenu().navigateToAccounts();
        rachunki.wyszukajRachunek("58 1910 1048 2511 0010 8881 0001");
        rachunki.wybierzSzczegolyRachunku("58 1910 1048 2511 0010 8881 0001","11");
    }


}
