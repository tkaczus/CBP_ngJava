package asseco.CBP.pages;

import asseco.CBP.components.Authorizations;
import asseco.CBP.components.Buttons;
import asseco.CBP.components.NavigationMenu;
import com.orasi.utils.date.DateTimeConversion;
import com.paulhammant.ngwebdriver.ByAngular;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static asseco.CBP.tests.WebDriverTestBase.findNgRepeatAndClick;
import static com.paulhammant.ngwebdriver.WaitForAngularRequestsToFinish.waitForAngularRequestsToFinish;

public class Payments {
    private static final By NOWA_PLATNOSC = Buttons.NOWA_PLATNOSC;
    private static final By TYP_PLATNOSCI = By.xpath("//div[@class='row-value']")
            .xpath("//a[@class='select2-choice ui-select-match']");
    private static final By LIST = By.xpath("a[@class='select2-choice ui-select-match']");
    private static final By LIST2 = By.tagName("a");
    private static final By PRZELEW_ZWYKLY = ByAngular.repeater("type in $select.items").row(0);
    private static final By PRZELEW_WLASNY = ByAngular.repeater("type in $select.items").row(1);
    private static final By PRZELEW_EUROPEJSKI = ByAngular.repeater("type in $select.items").row(2);
    private static final By PRZELEW_ZAGRANICZNY = ByAngular.repeater("type in $select.items").row(3);
    private static final By PRZELEW_PODATKU = ByAngular.repeater("type in $select.items").row(4);
    private static final By PRZELEW_DO_ZUS = ByAngular.repeater("type in $select.items").row(5);
    private static final By ODBIORCA = ByAngular.model("formData.formModel");
    private static final By ODBIORCA2 = By.name("templateSelect");
    private static final By RACHUNEK_ODBIORCY = ByAngular.model("formData.formModel.recipientAccountNo");
    private static final By KWOTA = ByAngular.model("formData.formModel.amount");
    private static final By KWOTA_WEWNATRZ = By.name("amount");
    private static final By TYTUL = ByAngular.model("formData.formModel.description");
    private static final By TYTUL_WEWNATRZ = By.name("description");
    private static final By WYSLIJ_PRZELEW = Buttons.WYSLIJ_PRZELEW;
    private static final By UTWORZ_NOWA_PLATNOSC = Buttons.UTWORZ_NOWA_PLATNOSC;
    private static final By DATA = ByAngular.model("formData.formModel.realizationDate"); //24.02.2016
    private static final By DATA2 = By.tagName("input");
    private static final By POWTARZAJ = By.name("optionalCheck");
    //PRZELEW_WLASNY
    private static final By Z_RACHUNKU = ByAngular.model("formData.formModel.remitterAccountId");
    private static final By NA_RACHUNEK = ByAngular.model("formData.formModel.beneficiaryAccountId");
    //	ng-repeat="account in $select.items"
//	PRZELEW_EUROP
    private static final By ODBIORCA_ADRES = ByAngular.model("formData.formModel.recipientAddress");
    private static final By ODBIORCA_ADRES2 = By.name("address");
    private static final By BICSWIFT = ByAngular.model("formData.formModel.recipientSwift");
    private static final By PRZEWALUTOWANIE = By.name("exchange");
    //US
    private static final By MIASTO = ByAngular.model("formData.formModel.recipientAddress");
    private static final By URZAD_SKARBOWY = ByAngular.model("formData.formModel.recipientName");
    private static final By GRUPA_PODATKOWA = ByAngular.model("formData.taxGroupId");
    private static final By SYMBOL_FORMULARZA = ByAngular.model("formData.formModel.formCode");
    private static final By TYP_OKRESU = ByAngular.model("formData.formModel.periodType");
    private static final By NUMER_OKRESU = ByAngular.model("formData.formModel.periodNumber");
    private static final By ROK_OKRESU = ByAngular.model("formData.formModel.periodYear");
    private static final By TYP_IDENTYFIKATORA = ByAngular.model("formData.formModel.idType");
    private static final By IDENTYFIKATOR = ByAngular.model("formData.formModel.idNumber");
    private static final By IDENTYFIKACJA_ZOBOWIAZAN = ByAngular.model("formData.formModel.obligationId");
    //ZUS
    private static final By NUMER_RACHUNKU_ZUS = RACHUNEK_ODBIORCY;
    private static final By TYP_WPLATY = ByAngular.model("formData.formModel.paymentType");
    private static final By DEKLARACJA = ByAngular.model("formData.formModel.declarationDate");
    private static final By NUMER_DEKLARACJI = ByAngular.model("formData.formModel.declarationNo");
    private static final By NIP_PLATNIKA = ByAngular.model("formData.formModel.nip");
    private static final By TYP_DRUGIEGO_IDENTYFIKATORA = ByAngular.model("formData.formModel.secondaryIdType");
    private static final By IDENTYFIKATOR_UZUPELNIAJACY = ByAngular.model("formData.formModel.secondaryIdNo");
    private static final By NUMER_DECYZJI = ByAngular.model("formData.formModel.decisionNo");
    private static final By AKCEPTUJ = Buttons.AKCEPTUJ;
    private static final By ZAPISZ_ZMIANY = Buttons.ZAPISZ_ZMIANY;
    private static final By WROC = ByAngular.cssContainingText(".controls-container","Wróć do pulpitu");
    //Zlecenie stale
    private static final By DZIEN_NASTEPNEJ_REALIZACJI = ByAngular.model("formData.formModel.executionDayInMonth");
    //choice in $select.items
    private static final By CYKL_REALIZACJI = ByAngular.model("periodCount");
    private static final By DATA_ZAKONCZENIA = ByAngular.model("endDate");
    //class="eb-checkbox eb-dbnext-theme"

    private final NavigationMenu navigationMenu;
    private WebDriver driver;
    private Authorizations podpis;

    public Payments(WebDriver driver) {
        this.driver = driver;
        this.navigationMenu = new NavigationMenu(driver);
        this.podpis = new Authorizations(driver);
    }

    public void uzupelnijPrzelewZwykly(String nazwaOdbiorcy, String rachunekOdbiorcy, String kwota, String tytul, String dataPrzelewu, boolean czyPowtarzac) {
        driver.findElement(NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(TYP_PLATNOSCI).click();
        driver.findElement(PRZELEW_ZWYKLY).click();
        driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
        driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(TYTUL).click();
        driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
        if (czyPowtarzac)
            driver.findElement(POWTARZAJ).click();
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(WYSLIJ_PRZELEW).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void uzupelnijPrzelewWlasny(String zRachunku, String naRahunek, String kwota, String tytul, String dataPrzelewu, boolean czyPowtarzac) {
        driver.findElement(NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(TYP_PLATNOSCI).click();
        driver.findElement(PRZELEW_WLASNY).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(NA_RACHUNEK).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", naRahunek);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(TYTUL).click();
        driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
        if (czyPowtarzac)
            driver.findElement(POWTARZAJ).click();
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(WYSLIJ_PRZELEW).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void uzupelnijPrzelewEuropejski(String nazwaOdbiorcy, String adresOdbiorcy, String rachunekOdbiorcy, String kodBicSwift, String zRachunku, String kwota, String tytul, String dataPrzelewu) {
        driver.findElement(NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(TYP_PLATNOSCI).click();
        driver.findElement(PRZELEW_EUROPEJSKI).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(ODBIORCA).findElement(ODBIORCA2).sendKeys(nazwaOdbiorcy);
        driver.findElement(ODBIORCA_ADRES).findElement(ODBIORCA_ADRES2).sendKeys(adresOdbiorcy);
        driver.findElement(BICSWIFT).sendKeys(kodBicSwift);
        driver.findElement(RACHUNEK_ODBIORCY).sendKeys(rachunekOdbiorcy);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(TYTUL).click();
        driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(PRZEWALUTOWANIE).click();
        driver.findElement(WYSLIJ_PRZELEW).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void edutujZlecenieStale(String kwota,String tytul, String dzienNastepnejRealizacji, String cyklRealizacji, String dataZakonczenia ){
        driver.findElement(TYTUL).clear();
        driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).clear();
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        driver.findElement(DZIEN_NASTEPNEJ_REALIZACJI).click();
        findNgRepeatAndClick("choice in $select.items", dzienNastepnejRealizacji);
        driver.findElement(CYKL_REALIZACJI).clear();
        driver.findElement(CYKL_REALIZACJI).sendKeys(cyklRealizacji);
        driver.findElement(DATA_ZAKONCZENIA).click();
        driver.findElement(DATA_ZAKONCZENIA).clear();
        driver.findElement(DATA_ZAKONCZENIA).sendKeys(dataZakonczenia);
        driver.findElement(ZAPISZ_ZMIANY).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(WROC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void edutujPrzelewOdroczony(String kwota,String tytul, String dataPrzelewu){
        driver.findElement(TYTUL).clear();
        driver.findElement(TYTUL).findElement(TYTUL_WEWNATRZ).sendKeys(tytul);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).clear();
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(ZAPISZ_ZMIANY).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(WROC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void uzupelnijPrzelewUS(String miasto, String urzadSkarbowy, String grupaPodatkowa, String symbolFormularza, String typOkresu, String numerOkresu,
                                   String rokOkresu, String typIdentyfikatora, String identyfikator, String zRachunku, String identyfikacjaZobowiazan, String kwota, String dataPrzelewu) {
        driver.findElement(NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(TYP_PLATNOSCI).click();
        driver.findElement(PRZELEW_PODATKU).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(MIASTO).findElement(LIST2).click();
        findNgRepeatAndClick("taxOfficeAddress in $select.items", miasto);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(URZAD_SKARBOWY).findElement(LIST2).click();
        findNgRepeatAndClick("taxOffice in $select.items", urzadSkarbowy);
        driver.findElement(GRUPA_PODATKOWA).findElement(LIST2).click();
        findNgRepeatAndClick("type in $select.items", grupaPodatkowa);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(SYMBOL_FORMULARZA).findElement(LIST2).click();
        findNgRepeatAndClick("taxFormCode in $select.items", symbolFormularza);
        driver.findElement(TYP_OKRESU).findElement(LIST2).click();
        findNgRepeatAndClick("type in $select.items", typOkresu);
        driver.findElement(NUMER_OKRESU).findElement(LIST2).click();
        findNgRepeatAndClick("number in $select.items", numerOkresu);
        driver.findElement(ROK_OKRESU).sendKeys(rokOkresu);
        driver.findElement(TYP_IDENTYFIKATORA).findElement(LIST2).click();
        findNgRepeatAndClick("type in $select.items", typIdentyfikatora);
        driver.findElement(IDENTYFIKATOR).sendKeys(identyfikator);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(IDENTYFIKACJA_ZOBOWIAZAN).sendKeys(identyfikacjaZobowiazan);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(WYSLIJ_PRZELEW).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void edutujPrzelewUS(String kwota, String dataPrzelewu){
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).clear();
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(ZAPISZ_ZMIANY).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(WROC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void uzupelnijPrzelewZUS(String numerRachunkuZUS, String typWplaty, String deklaracja, String numerDeklaracji, String nipPlatnika, String typIdentyfikatoraUzupelniajacego,
                                    String identyfikatorUzupelniajacy, String zRachunku, String numerDecyzji, String kwota, String dataPrzelewu) {
        driver.findElement(NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(TYP_PLATNOSCI).click();
        driver.findElement(PRZELEW_DO_ZUS).click();
        waitForAngularRequestsToFinish(driver);
        driver.findElement(NUMER_RACHUNKU_ZUS).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", numerRachunkuZUS);
        driver.findElement(TYP_WPLATY).findElement(LIST2).click();
        findNgRepeatAndClick("type in $select.items", typWplaty);
        driver.findElement(DEKLARACJA).sendKeys(deklaracja);
        driver.findElement(NUMER_DEKLARACJI).sendKeys(numerDeklaracji);
        driver.findElement(NIP_PLATNIKA).sendKeys(nipPlatnika);
        driver.findElement(TYP_DRUGIEGO_IDENTYFIKATORA).findElement(LIST2).click();
        findNgRepeatAndClick("type in $select.items", typIdentyfikatoraUzupelniajacego);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(IDENTYFIKATOR_UZUPELNIAJACY).sendKeys(identyfikatorUzupelniajacy);
        driver.findElement(Z_RACHUNKU).findElement(LIST2).click();
        findNgRepeatAndClick("account in $select.items", zRachunku);
        driver.findElement(NUMER_DECYZJI).sendKeys(numerDecyzji);
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(WYSLIJ_PRZELEW).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(UTWORZ_NOWA_PLATNOSC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public void edutujPrzelewZUS(String kwota, String dataPrzelewu){
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).clear();
        driver.findElement(KWOTA).findElement(KWOTA_WEWNATRZ).sendKeys(kwota);
        if (dataPrzelewu != null) {
            driver.findElement(DATA).findElement(DATA2).click();
            driver.findElement(DATA).findElement(DATA2).clear();
            driver.findElement(DATA).findElement(DATA2).sendKeys(dataPrzelewu);
        }
        driver.findElement(ZAPISZ_ZMIANY).click();
        waitForAngularRequestsToFinish(driver);
        podpis.AuthorizeTan(AKCEPTUJ);
        waitForAngularRequestsToFinish(driver);
        driver.findElement(WROC).click();
        waitForAngularRequestsToFinish(driver);
    }

    public NavigationMenu navigationMenu() {
        return navigationMenu;
    }
}
