/**
 * Created by acme on 16.05.17.
 * This is main RUN Class
 */

public class Purchases
{
    public static void main(String[] args) {

        DBOperation.createDatabase("database.db");
        DBOperation.createTable();

        while(true){

            ConsoleReader.consoleReader();
        }

    }








}



