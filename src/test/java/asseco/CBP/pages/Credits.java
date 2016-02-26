package asseco.CBP.pages;

import asseco.CBP.components.Buttons;
import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static asseco.CBP.tests.WebDriverTestBase.findNgRepeatAndClick;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class Credits {

    private static final By SZUKAJ = By.className("toolbar-icons");
    private static final By WYSZUKAJ = ByAngular.model("ebSearchText");
    //	account in accountList.content | filter: {wrappedText : filterOptions.accountName}
    private static final By RAMKA_RACHUNKU = ByAngular.repeater("credit in creditList.content | filter: {wrappedText : filterOptions.title}").row(0);
    private static final By ROZWNIN = By.tagName("button");
    private static final By SPLAC_RATE = ByAngular.cssContainingText(".option-label", "Spłać ratę");
    private static final By Z_RACHUNKU = ByAngular.model("currentObject.repayment.remitterAccountId");
    private static final By LIST2 = By.tagName("a");
    private static final By KWOTA = ByAngular.model("currentObject.repayment.amount");
    private static final By KWOTA_WEWNATRZ = By.name("amount");
    private static final By SPLAC_RATE_BUTTON = Buttons.SPLAC_RATE;
    private static final By AKCEPTUJ = Buttons.AKCEPTUJ;
    private static final By WROC_DO_PULPITU = Buttons.WROC_DO_PULPITU;


    private final NavigationMenu navigationMenu;
    private WebDriver driver;

    public Credits(WebDriver driver) {
        this.driver = driver;
        this.navigationMenu = new NavigationMenu(driver);
    }

    public void wyszukajRachunek(String nrb) {
        driver.findElement(SZUKAJ).click();
        driver.findElement(WYSZUKAJ).sendKeys(nrb);
        Assert.assertEquals(driver.findElement(RAMKA_RACHUNKU).isDisplayed(), true);
    }

    /**
     * if kwota null splata calkowita
     *
     * @param zRachunku
     * @param kwota
     */
    public void splacRate(String zRachunku, String kwota) {
        driver.findElement(RAMKA_RACHUNKU).findElement(ROZWNIN).click();
        driver.findElement(SPLAC_RATE).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        if (kwota != null) {
            driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).clear();
            driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        }
        driver.findElement(SPLAC_RATE_BUTTON).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(AKCEPTUJ).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(WROC_DO_PULPITU).click();
    }

    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }
}
