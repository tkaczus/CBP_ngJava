package asseco.CBP.components;

import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;


public class Authorizations {

	   private static final By tanX = ByAngular.model("ngModel.tanX");
	   private static final By tanY = ByAngular.model("ngModel.tanY");
	   private static final By sms = ByAngular.model("ngModel.sms");
	   private WebDriver driver;

	   public Authorizations(WebDriver driver) {
	      this.driver =  driver;
	   }
	 
	   public void AuthorizeTan(By Przycisk) {
		  try {
			   driver.findElement(tanX).sendKeys("11");
			   driver.findElement(tanY).sendKeys("11");
		   }
		  catch (WebDriverException e) {
			  System.out.println("Brak autoryzacji TAN");
		}
		   try {
			   driver.findElement(sms).sendKeys("11111111");
		   }
		   catch (WebDriverException e) {
			   System.out.println("Brak autoryzacji SMS");
		   }
		   finally {
			  driver.findElement(Przycisk).click();
		  }

	   }
	   


}
