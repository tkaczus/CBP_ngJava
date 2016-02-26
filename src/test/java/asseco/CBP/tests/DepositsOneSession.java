package asseco.CBP.tests;

import asseco.CBP.pages.Deposits;
import org.testng.annotations.Test;

import java.io.IOException;


public class DepositsOneSession extends WebDriverTestBase {

    @Test
    public void testDodajDepozyt() throws IOException {
        Deposits deposits = homePage.navigationMenu().navigateToDeposits();
        deposits.uzupelnijLokate("1", null, "2222", "Przedłuż z odsetkami");
    }


}
