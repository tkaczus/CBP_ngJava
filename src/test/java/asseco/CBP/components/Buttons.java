package asseco.CBP.components;

import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class Buttons {

	public static final By WYLOGUJ = ByAngular.buttonText("Wyloguj");
	public static final By ZALOGUJ_PONOWNIE = ByAngular.buttonText("Zaloguj ponownie");
	public static final By AKCEPTUJ = ByAngular.buttonText("Akceptuj");
	public static final By NOWA_PLATNOSC = ByAngular.buttonText("Nowa płatność");
	public static final By WYSLIJ_PRZELEW = ByAngular.buttonText("Wyślij przelew");
	public static final By UTWORZ_NOWA_PLATNOSC = ByAngular.buttonText("Utwórz nową płatność");
}
