package asseco.CBP.tests;

import asseco.CBP.pages.Awizo;
import org.testng.annotations.Test;

import java.io.IOException;

public class AwizoOneSession extends WebDriverTestBase {

    @Test
    public void testDodajAwizo() throws IOException {
        Awizo awizo = homePage.navigationMenu().navigateToAwizo();
        awizo.uzupelnijAwizo("14", "25000", null, null);
    }


}
