
import java.sql.*;

/**
 * Created by acme on 19.05.17.
 * This Class has Methods to operate with database
 */


public class DBOperation {
        private static String fileName = "database.db";
        private static String dbPath = "jdbc:sqlite:";
        private static String url = dbPath + fileName;
         /**
          *  Connect to a sample database
          *  don't using ((
          */

         /*public static void connect() {
             Connection conn = null;
             try {
                 // db parameters

                 // create a connection to the database
                 conn = DriverManager.getConnection(url);

                 System.out.println("Connection to SQLite has been established.");

             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             } finally {
                 try {
                     if (conn != null) {
                         conn.close();
                     }
                 } catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                 }
             }
         }*/

         public static void createDatabase(String fileName) {

              String url = dbPath + fileName;

             try (Connection conn = DriverManager.getConnection(url)) {
                 if (conn != null) {
                     DatabaseMetaData meta = conn.getMetaData();
                     System.out.println("The driver name is " + meta.getDriverName());
                     //System.out.println("A new database has been created.");
                 }

             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }
         }

    /**
     * Create a new table in the database
     *
     */
    public static void createTable() {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS bouthProduct (\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	datestr text NOT NULL,\n"
                + "	sumdouble real,\n"
                + " valuta text,\n"
                + " prodName text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Database is avalible");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void dropTable() {

        String sql = "DROP TABLE IF EXISTS bouthProduct;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        System.out.println("DBTable is DROPED!");
    }



    /*private Connection connectInsert() {
        // SQLite connection string
        String url = DBOperation.url+ fileName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
*/
    /**
     * Insert a new row into the warehouses table
     *
     * @param datestr
     * @param sumdouble
     * @parem valuta
     * @param productName
     */
    public static void insert(String datestr, double sumdouble, String valuta, String productName) {
        String sql = "INSERT INTO bouthProduct (datestr,sumdouble,valuta,prodName) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datestr);
            pstmt.setDouble(2, sumdouble);
            pstmt.setString(3, valuta);
            pstmt.setString(4, productName);
            pstmt.executeUpdate();
            System.out.println(datestr+" "+sumdouble+" "+valuta+" "+productName+" added to database" );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void insert(double equivalent, int id) {
        String sql = "INSERT INTO bouthProduct (equivalent) VALUES(?) WHERE (id = ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, equivalent);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println(equivalent+" added to database" );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * DELETE a row at date from the bouthProduct table
     *
     * @param datestr
     *
     */
    public static void delete (String datestr) {
        String sql = "DELETE FROM bouthProduct WHERE datestr = ?";
                //"INSERT INTO bouthProduct (datestr,sumdouble,valuta,prodName) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, datestr);
            pstmt.executeUpdate();
            System.out.println("Data at "+datestr+" deleted from database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAll(){
        String sql = "SELECT datestr, sumdouble, valuta, prodName FROM bouthProduct ORDER BY datestr";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            String s = " ";
            while (rs.next()) {
                double d;
                String v;
                String pN;

                if (s.equals(rs.getString("datestr"))) {
                    d = rs.getDouble("sumdouble");
                    v = rs.getString("valuta");
                    pN = rs.getString("prodName");
                    System.out.println(pN + " " + d + " " + v);

                }
                else {
                    s =  rs.getString("datestr");
                    d = rs.getDouble("sumdouble");
                    v = rs.getString("valuta");
                    pN = rs.getString("prodName");
                    //System.out.println();
                    System.out.println("\n" + s);
                    System.out.println(pN + " " + d + " " + v);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void selectAll(String datestr){
        String sql = "SELECT datestr, sumdouble, valuta, prodName FROM bouthProduct WHERE datestr = ?";

        try (Connection conn = DriverManager.getConnection(url);

             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1, datestr);

            ResultSet rs  = pstmt.executeQuery();


            // loop through the result set
            String s = " ";
            while (rs.next()) {
                double d;
                String v;
                String pN;

                if (s.equals(rs.getString("datestr"))) {
                    d = rs.getDouble("sumdouble");
                    v = rs.getString("valuta");
                    pN = rs.getString("prodName");
                    System.out.println(pN + " " + d + " " + v);

                }
                else {
                    s =  rs.getString("datestr");
                    d = rs.getDouble("sumdouble");
                    v = rs.getString("valuta");
                    pN = rs.getString("prodName");
                    System.out.println("\n" + s);
                    System.out.println(pN + " " + d + " " + v);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void selectTotal(String valutadb ) {
        String sql = "SELECT  valuta , sumdouble FROM bouthProduct ";

        try (Connection conn = DriverManager.getConnection(url);

             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){


            // loop through the result set
            String s = valutadb;
            String v;
            double d;
            double sum = 0.0;
            while (rs.next()) {
                v = rs.getString("valuta");
                d = rs.getDouble("sumdouble");
                if (s.equals(v)) {
                   sum = sum + d;

                }
                else {

                    double de;
                    de = ExchangeTo.getFixerRates(valutadb, v);
                    sum = sum +(d*de);
                }

            }

            System.out.println("TOTAL in "+valutadb+" "+String.format("%.2f", sum));

        } catch (SQLException e) {

             System.out.println(e.getMessage());

        } catch (Exception e) {
           // System.out.println(e);
            System.out.println("Unavalible Currency");
        }
    }




}

