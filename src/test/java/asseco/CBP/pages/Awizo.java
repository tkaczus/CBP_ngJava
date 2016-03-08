package asseco.CBP.pages;

import asseco.CBP.components.Authorizations;
import asseco.CBP.components.Buttons;
import asseco.CBP.components.NavigationMenu;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static asseco.CBP.tests.WebDriverTestBase.findNgRepeatAndClick;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class Awizo {
    private static final By NOWE_AIZOWANIE = Buttons.NOWE_AWIZOWANIE;
    private static final By Z_RACHUNKU = ByAngular.model("formData.formModel.accountId");
    private static final By LIST2 = By.tagName("a");
    private static final By KWOTA = ByAngular.model("formData.formModel.amount");
    private static final By KWOTA_WEWNATRZ = By.name("amount");
    private static final By MIEJSCE_WYPLATY = ByAngular.model("formData.formModel.paymentAdviceNoteBranchId");
    private static final By DATA_AWIZO = ByAngular.model("formData.formModel.realizationDate");
    private static final By CHECKBOX = By.xpath("//div[@eb-model='formData.regulation']");
//    eb-model="formData.regulation"
    private static final By WYSLIJ_AWIZO = Buttons.WYSLIJ_AWIZO;
    private static final By AKCEPTUJ = Buttons.AKCEPTUJ;


    private final NavigationMenu navigationMenu;
    private WebDriver driver;
    private Authorizations podpis;
    private String zRachunku;

    public Awizo(WebDriver driver) {
        this.driver = driver;
        this.navigationMenu = new NavigationMenu(driver);
        this.podpis = new Authorizations(driver);
    }

    /**
     * If miejsceWyplaty=null
     * Wybiera pierwsze na liscie
     * if dataWyplaty=null
     * nie zmienia daty
     **/
    public void uzupelnijAwizo(String zRachunku, String kwota, String miejsceWyplaty, String dataWyplaty) {
        driver.findElement(NOWE_AIZOWANIE).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(MIEJSCE_WYPLATY).findElement(LIST2).click();
        if (miejsceWyplaty == null) {
            driver.findElement(ByAngular.repeater("branch in $select.items").row(0)).click();
        } else {
            findNgRepeatAndClick("branch in $select.items", miejsceWyplaty);
        }
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (miejsceWyplaty != null) {
            driver.findElement(DATA_AWIZO).clear();
            driver.findElement(DATA_AWIZO).sendKeys(dataWyplaty);
        }
        driver.findElement(CHECKBOX).click();
        driver.findElement(WYSLIJ_AWIZO).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
    }


    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }
}
