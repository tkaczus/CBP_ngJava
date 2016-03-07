package asseco.CBP.dataDriven;

import com.orasi.utils.database.Database;
import com.orasi.utils.database.databaseImpl.OracleDatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public ClientData(){

        db = new OracleDatabase(host, port, database);
        db.setDbUserName(username);
        db.setDbPassword(password);
    }

    public String getNrb()  {
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
        System.out.println("USER_LOGIN=" + obj.getProperty("USER_LOGIN"));
        String sql="select * from customers";
        Object[][] wynik=db.getResultSet(sql);
        return nrb= wynik[1][0].toString();
    }

    public String getNrbEUR() {
        return nrbEUR;
    }

    public String getNrbCredit() {
        return nrbCredit;
    }

}
