package asseco.CBP.tests;

import asseco.CBP.pages.Credits;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreditsOneSession extends WebDriverTestBase {

    @Test
    public void testSplacKredyt() throws IOException {
        Credits kredyt = homePage.navigationMenu().navigateToCredits();
        kredyt.wyszukajRachunek("471910");
        kredyt.splacRate("41", null);
    }


}
