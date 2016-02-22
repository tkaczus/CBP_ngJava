package asseco.CBP.components;

import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static asseco.CBP.components.Buttons.*;


public class Authorizations {

	   private static final By tanX = ByAngular.model("ngModel.tanX");
	   private static final By tanY = ByAngular.model("ngModel.tanY");
	   private WebDriver driver;

	   public Authorizations(WebDriver driver) {
	      this.driver =  driver;
	   }
	 
	   public void AuthorizeTan() {
		   driver.findElement(tanX).sendKeys("11");
		   driver.findElement(tanY).sendKeys("11");
		   driver.findElement(AKCEPTUJ).click();
	   }
	   


}
