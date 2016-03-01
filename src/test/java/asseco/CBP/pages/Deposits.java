package asseco.CBP.pages;

import asseco.CBP.components.Authorizations;
import asseco.CBP.components.Buttons;
import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static asseco.CBP.tests.WebDriverTestBase.findNgRepeatAndClick;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class Deposits {
    private static final By NOWA_LOKATA = Buttons.NOWA_LOKATA;
    private static final By Z_RACHUNKU = ByAngular.model("depositForm.formModel.openingAccountId");
    private static final By LIST = By.xpath("a[@class='select2-choice ui-select-match']");
    private static final By LIST2 = By.tagName("a");
    private static final By RODZAJ_LOKATY = ByAngular.model("depositForm.formModel.depositTypeId");
    private static final By DYSPOZYCJA = ByAngular.model("depositForm.formModel.renewalOptionType");
    private static final By KWOTA = ByAngular.model("depositForm.formModel.amount");
    private static final By KWOTA_WEWNATRZ = By.name("amount");
    private static final By ZALOZ_LOKATE = Buttons.ZALOZ_LOKATE;
    private static final By AKCEPTUJ = Buttons.AKCEPTUJ;
    private static final By ZALOZ__NOWA_LOKATE = Buttons.ZALOZ__NOWA_LOKATE;


    private final NavigationMenu navigationMenu;
    private WebDriver driver;
    private Authorizations podpis;

    public Deposits(WebDriver driver) {
        this.driver = driver;
        this.navigationMenu = new NavigationMenu(driver);
        this.podpis = new Authorizations(driver);
    }

    /**
     * if rodzaj lokaty null pierwsza z listy
     * Przedłuż z odsetkami
     * Przedłuż bez odsetek
     * Prześlij na rachunek
     **/
    public void uzupelnijLokate(String zRachunku, String rodzajLokaty, String kwota, String dyspozycja) {
        driver.findElement(NOWA_LOKATA).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(RODZAJ_LOKATY).findElement(LIST2).click();
        if (rodzajLokaty == null) {
            driver.findElement(ByAngular.repeater("deposit in $select.items").row(0)).click();
        } else {
            findNgRepeatAndClick("deposit in $select.items", rodzajLokaty);
        }
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(DYSPOZYCJA).findElement(LIST2).click();
        findNgRepeatAndClick("disposition in $select.items", dyspozycja);
        driver.findElement(ZALOZ_LOKATE).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(AKCEPTUJ).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(ZALOZ__NOWA_LOKATE).click();
        ;
    }


    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }
}
