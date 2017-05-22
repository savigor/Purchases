
import java.util.regex.*;
import java.util.Scanner;

/**
 * Created by acme on 17.05.17.
 */
import java.util.*;

public class ConsoleReader {

    private static String str;
    private static String comm = null;
    private static String datestr = null;
    private static String valuta = null;
    private static String productName = null;
    private static double sumDouble = 0.0;

    public static void consoleReader() {

        //читаєм ввод з командної строки
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        StringTokenizer stk = new StringTokenizer(str);

        //Визначаємо чи відповідає ввід межам даних та
        // парсимо строку вводу до відповідних змінних
        if (checkadd(str) == true){
            comm = stk.nextToken();
            datestr =  stk.nextToken();
            sumDouble = Double.parseDouble(stk.nextToken());
            valuta = stk.nextToken();
            productName = stk.nextToken();
            if(stk.hasMoreTokens()){
                productName = productName + " " + stk.nextToken();
            }
            DBOperation.insert(datestr, sumDouble, valuta, productName);
            DBOperation.selectAll();
        }
        else if (checklist(str) == true) {
            comm = stk.nextToken();
            DBOperation.selectAll();
        }
        else if (checkclear(str) == true) {
            comm = stk.nextToken();
            datestr = stk.nextToken();
            DBOperation.selectAll(datestr);
            DBOperation.delete(datestr);
        }
        else if (checktotal(str) == true) {
            comm = stk.nextToken();
            valuta = stk.nextToken();
            DBOperation.selectTotal(valuta);
        }

        else if (checkcreateDb(str) == true) {
            comm = stk.nextToken();
            DBOperation.createDatabase("database.db");
            DBOperation.createTable();
        }
        else if (checkdeletetabl(str) == true) {
            comm = stk.nextToken();
            DBOperation.dropTable();
        }
        else {
            System.out.println("Input error");
        }
        //System.out.println(str);
        //System.out.println("add " + checkadd(str) + " list " + checklist(str) +
        //        " clear " + checkclear(str) + " total " + checktotal(str));
        //System.out.println( " " + comm +" "+datestr+" " + sumDouble + " " + valuta + " " + productName );

    } //END MAIN METHOD

    //!!!методи для перевірки відповідності введеної строки
    // команда add
    private static boolean checkadd(String str){
        Pattern pattern = Pattern.compile("^add\\s+[19|20]{2}[0-9]{2}-[0-9]{2}-[0-9]{2}\\s+([0-9]+|[0-9]+\\.[0-9]{1,2})\\s+([A-Z]{3})\\s+((\\w{3,20})|(\"\\w{3,20}\\s+\\w{3,20}\"))$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    //команда list
    private static boolean checklist(String str){
        Pattern pattern = Pattern.compile("^list$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    //команда clear
    private static boolean checkclear(String str){
        Pattern pattern = Pattern.compile("^clear\\s[0-9]{4}-[0-9]{2}-[0-9]{2}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    //команда total
    private static boolean checktotal(String str){
        Pattern pattern = Pattern.compile("^total\\s([A-Z]{3})$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    private static boolean checkcreateDb(String str){
        Pattern pattern = Pattern.compile("^createdb$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static boolean checkdeletetabl(String str){
        Pattern pattern = Pattern.compile("^deletetabl$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
} //END Class

