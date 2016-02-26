package asseco.CBP.components;

import asseco.CBP.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class NavigationMenu {

    private static final By LEWE_MENU = By.className("left-menu-trigger");
    private static final By NAVIGATION_ITEM_ACCOUNTS = ByAngular.repeater("element in elements").row(1);
    private static final By NAVIGATION_ITEM_PAYMENTS = ByAngular.repeater("element in elements").row(2);
    private static final By NAVIGATION_ITEM_PPRZEPLYWY = ByAngular.repeater("element in elements").row(3);
    private static final By NAVIGATION_ITEM_DEPOSITS = ByAngular.repeater("element in elements").row(4);
    private static final By NAVIGATION_ITEM_CREDITS = ByAngular.repeater("element in elements").row(6);
    private static final By NAVIGATION_ITEM_AWIZO= ByAngular.repeater("element in elements").row(10);
    private static final By NAVIGATION_ITEM_PAYMENTS2 = By.linkText("Płatności");
    private WebDriver driver;

    public NavigationMenu(WebDriver driver) {
        this.driver = driver;

    }

    private void OpenMenuIfClosed() {
        if (!driver.findElement(NAVIGATION_ITEM_PAYMENTS).isDisplayed()) {
            driver.findElement(LEWE_MENU).click();
        }
        waitForAngularRequestsToFinish(driver);
    }

    public Payments navigateToPayments() {
        OpenMenuIfClosed();
        driver.findElement(NAVIGATION_ITEM_PAYMENTS).click();
        waitForAngularRequestsToFinish(driver);
        return new Payments(driver);
    }

    public Accounts navigateToAccounts() {
        OpenMenuIfClosed();
        driver.findElement(NAVIGATION_ITEM_ACCOUNTS).click();
        waitForAngularRequestsToFinish(driver);
        return new Accounts(driver);
    }

    public Deposits navigateToDeposits() {
        OpenMenuIfClosed();
        driver.findElement(NAVIGATION_ITEM_DEPOSITS).click();
        waitForAngularRequestsToFinish(driver);
        return new Deposits(driver);
    }

    public Awizo navigateToAwizo() {
        OpenMenuIfClosed();
        driver.findElement(NAVIGATION_ITEM_AWIZO).click();
        waitForAngularRequestsToFinish(driver);
        return new Awizo(driver);
    }

    public Credits navigateToCredits() {
        OpenMenuIfClosed();
        driver.findElement(NAVIGATION_ITEM_CREDITS).click();
        waitForAngularRequestsToFinish(driver);
        return new Credits(driver);
    }

    public LoginPage navigateToLoginPage() {
        return new LoginPage(driver);
    }
}
