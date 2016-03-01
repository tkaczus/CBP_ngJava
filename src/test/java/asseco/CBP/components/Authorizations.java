package asseco.CBP.components;

import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static asseco.CBP.components.Buttons.*;


public class Authorizations {

	   private static final By tanX = ByAngular.model("ngModel.tanX");
	   private static final By tanY = ByAngular.model("ngModel.tanY");
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
			  System.out.println("Brak autoryzacji");
//		assertThat(e.getMessage(), startsWith("$scope variable 'locationnnnnnnnn' not found in same scope as the element passed in."));
		}
		   finally {
			  driver.findElement(Przycisk).click();
		  }

	   }
	   


}
