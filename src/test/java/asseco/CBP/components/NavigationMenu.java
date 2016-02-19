package asseco.CBP.components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.ByAngular;

import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;
import asseco.CBP.pages.LoginPage;
import asseco.CBP.pages.Payments;

public class NavigationMenu {

	   private static final By LEWE_MENU = By.className("left-menu-trigger");
	   private static final By NAVIGATION_ITEM_PAYMENTS = ByAngular.repeater("element in elements").row(2);
	   private static final By NAVIGATION_ITEM_PAYMENTS2 = By.linkText("Płatności");
	   private WebDriver driver;
	   
	   public NavigationMenu(WebDriver driver) {
	      this.driver =  driver;
	      
	   }
	 
	   public Payments navigateToPayments() {
//		 WebElement firstname = driver.findElement(ByAngular.model("username"));
		 waitForAngularRequestsToFinish(driver);
		 if (!driver.findElement(NAVIGATION_ITEM_PAYMENTS).isDisplayed()) {
             driver.findElement(LEWE_MENU).click();
         }
           driver.findElement(NAVIGATION_ITEM_PAYMENTS).click();
//		 WebElement we = driver.findElement(NAVIGATION_ITEM_PAYMENTS);
//	     we.click();
	     waitForAngularRequestsToFinish(driver);
	     return new Payments(driver);
	   }
	   

		public LoginPage navigateToLoginPage() {
			return new LoginPage(driver);
		}
}
