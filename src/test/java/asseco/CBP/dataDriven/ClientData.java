package asseco.CBP.dataDriven;

import com.orasi.utils.database.Database;
import com.orasi.utils.database.databaseImpl.OracleDatabase;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Lukasz.Tkaczyk on 2016-03-04.
 */
public class ClientData {

    public String nrb = null;
    public String nrbEUR = null;
    public String nrbCredit = null;

    private static String host = "10.17.201.79";
    private static String port = "1521";
    private static String database = "cloud38";
    private static String username = "DB_SBI_OWNER";
    private static String password = "admin";
    private Database db = null;

    public ClientData() {
        db = new OracleDatabase(host, port, database);
        db.setDbUserName(username);
        db.setDbPassword(password);
    }

    @NotNull
    private Properties returnFromProperties() {
        Properties obj = new Properties();
        FileInputStream objfile = null;
        try {
            objfile = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\db.user.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            obj.load(objfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * Zwraca dwa piersze znaki numeru nrb PLN dla podanego loginu
     * jesli znadzie rachunki o dostenpnych srodkach > 10000 PLN
     * @return
     */
    public String getNrbShort() {
        Object[][] wynik = new Object[0][];
        try {
            Properties obj = returnFromProperties();
            System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
            String sql = "select t.nrb, t.id_umowy,r.dostepne_srodki from UMOWA t , RACHUNEK r , Uzytkownik u where u.login=" +
                    "'" + obj.getProperty("USER_LOGIN") + "'" +
                    " and t.modulo in \n" +
                    "(select t.modulo from WEKTOR_AUTORYZACJI t where t.id_uzytkownika=u.id_uzytkownika and t.typ_operacji='OWNR' and t.typ_produktu='R')\n" +
                    "and t.typ_produktu='R' and t.status_umowy='AK' and r.id_rachunku=t.id_umowy and t.waluta='PLN'\n" +
                    "group by t.id_umowy,r.dostepne_srodki,t.nrb \n" +
                    "HAVING SUM(r.dostepne_srodki) > 10000\n" +
                    "order by r.dostepne_srodki desc";
            wynik = db.getResultSet(sql);
            nrb = wynik[1][0].toString().substring(0, 2);
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("BRAK USERA W BAZIE !!!");
            throw new org.testng.TestNGException("BRAK USERA W BAZIE !!!");
        }
        return nrb;
    }

    /**
     * Zwraca numer nrb PLN dla podanego loginu
     * jesli znadzie rachunki o dostenpnych srodkach > 10000 PLN
     * @return
     */
    public String getNrbLong() {
        Object[][] wynik = new Object[0][];
        try {
            Properties obj = returnFromProperties();
            System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
            String sql = "select t.nrb, t.id_umowy,r.dostepne_srodki from UMOWA t , RACHUNEK r , Uzytkownik u where u.login=" +
                    "'" + obj.getProperty("USER_LOGIN") + "'" +
                    " and t.modulo in \n" +
                    "(select t.modulo from WEKTOR_AUTORYZACJI t where t.id_uzytkownika=u.id_uzytkownika and t.typ_operacji='OWNR' and t.typ_produktu='R')\n" +
                    "and t.typ_produktu='R' and t.status_umowy='AK' and r.id_rachunku=t.id_umowy and t.waluta='PLN'\n" +
                    "group by t.id_umowy,r.dostepne_srodki,t.nrb \n" +
                    "HAVING SUM(r.dostepne_srodki) > 10000\n" +
                    "order by r.dostepne_srodki desc";
            wynik = db.getResultSet(sql);
            nrb = wynik[1][0].toString();
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("BRAK USERA W BAZIE !!!");
            throw new org.testng.TestNGException("BRAK USERA W BAZIE !!!");
        }
        return nrb;
    }

    private String formatToPLN(String saldo) {
        Locale locale = new Locale("pl", "PL");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(Double.valueOf(saldo));
        moneyString=moneyString.replaceAll("zł","PLN");
        System.out.println(saldo);
        System.out.println(moneyString);
        return moneyString;
    }

    /**
     * Zwraca dostępne środki dla danego numeru nrb
     * @return
     */
    public String getAwaibleFromNRBLong(String nrb) {
        Object[][] wynik = new Object[0][];
        try {
            Properties obj = returnFromProperties();
            System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
            String sql = "select t.saldo_na_date_waluty from RACHUNEK t,UMOWA u where t.id_rachunku=u.id_umowy and u.nrb='"+nrb+"'";
            wynik = db.getResultSet(sql);
            nrb = wynik[1][0].toString();
        }
        catch (java.lang.ArrayIndexOutOfBoundsException e){
            System.out.println("BRAK USERA W BAZIE !!!");
            throw new org.testng.TestNGException("BRAK USERA W BAZIE !!!");
        }
        return formatToPLN(nrb);
    }
    /**
     * Zwraca dwa piersze znaki numeru nrb w EUR dla podanego loginu
     * jesli znadzie rachunki o dostenpnych srodkach > 100 EUR
     * @return
     */
    public String getNrbEUR() {
        Properties obj = returnFromProperties();
        System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
        String sql = "select t.nrb, t.id_umowy,r.dostepne_srodki from UMOWA t , RACHUNEK r , Uzytkownik u where u.login=" +
                "'" + obj.getProperty("USER_LOGIN") + "'" +
                " and t.modulo in \n" +
                "(select t.modulo from WEKTOR_AUTORYZACJI t where t.id_uzytkownika=u.id_uzytkownika and t.typ_operacji='OWNR' and t.typ_produktu='R')\n" +
                "and t.typ_produktu='R' and t.status_umowy='AK' and r.id_rachunku=t.id_umowy and t.waluta='EUR'\n" +
                "group by t.id_umowy,r.dostepne_srodki,t.nrb \n" +
                "HAVING SUM(r.dostepne_srodki) > 100\n" +
                "order by r.dostepne_srodki desc";
        Object[][] wynik = db.getResultSet(sql);
        return nrbEUR = wynik[1][0].toString().substring(0, 2);
    }

    /**
     * Zwraca numer nrb dowolnego kredytu dla podanego loginu
     * @return
     */
    public String getNrbCredit() {
        Properties obj = returnFromProperties();
        System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
        String sql = "select t.nrb, t.id_umowy,k.oproc_trans_ratalnych from UMOWA t , KREDYT k , Uzytkownik u where " +
                "u.login='" + obj.getProperty("USER_LOGIN") + "' " +
                "and t.modulo in \n" +
                "(select t.modulo from WEKTOR_AUTORYZACJI t where t.id_uzytkownika=u.id_uzytkownika and t.typ_operacji='OWNR' and t.typ_produktu='R')\n" +
                "and t.typ_produktu='K' and t.status_umowy='AK' and k.id_kredytu=t.id_umowy ";
        Object[][] wynik = db.getResultSet(sql);
        return nrbCredit = wynik[1][0].toString();
    }

}
