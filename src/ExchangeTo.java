import com.google.gson.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by acme on 21.05.17.
 * This Class get the JSON data from Fixer.io
 * and return doble to exchange currency
 *
 *
 */


public class ExchangeTo {

    //private static
    //private static double  result;



    /**
     * This method get the JSON data from Fixer.io
     * and return doble to exchange currency
     * @param valuta in db
     * @param toValuta  transmited from cmd "total"
     *
     *
     */

    public static double getFixerRates(String valuta, String toValuta) throws Exception {

        String jsonstr = new String();
        try {
            // build a URL
            String link = "http://api.fixer.io/latest?base=" + toValuta;

            URL url = new URL(link);


            Scanner scan = new Scanner(url.openStream());
            // read string from the URL
            jsonstr = scan.nextLine();

        } catch (Exception e) {
            System.out.println("Currency error");
        }

            JsonObject jobj = new Gson().fromJson(jsonstr, JsonObject.class);

            //jobj to jobj which has data of rates
            jobj = jobj.get("rates").getAsJsonObject();

            //pars to double
            double result = jobj.get(valuta).getAsDouble();
            //System.out.println(result);

            return result;
    }
} //END OF Class
