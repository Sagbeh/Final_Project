import sun.reflect.generics.repository.ConstructorRepository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class DB {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //Create the database "RecordStore" in your database
    public static final String DB_CONNECTION_URL = "jdbc:mysql://localhost/RecordStore?useLegacyDatetimeCode=false&serverTimezone=America/Chicago";
    public static final String USER = "sam";
    public static final String PASSWORD = "agbeh";
    public static final String C_TABLE = "consignors";
    public static final String R_TABLE = "records";
    public static final String S_TABLE = "sales";
    public static final String I_TABLE = "invoices";
    public static final String CID_COL = "cid";
    public static final String NAME_COL = "name";
    public static final String PHONE_COL = "phone_number";
    public static final String RID_COL = "rid";
    public static final String ARTIST_COL = "artist";
    public static final String TITLE_COL = "title";
    public static final String SALESPRICE_COL = "sales_price";
    public static final String DATEADDED_COL = "date_added";
    public static final String STATUS_COL = "status";
    public static final String NOTIFIED_COL = "notified";
    public static final String SOLD_COL = "sold";
    public static final String SID_COL = "sid";
    public static final String SOLDDATE_COL = "sold_date";
    public static final String SOLDPRICE_COL = "sold_price";
    public static final String CIDPROFIT_COL = "cid_profit";
    public static final String STOREPROFIT_COL = "store_profit";
    public static final String AMOUNTPAID_COL = "amount_paid";
    public static final String PAYMENTDATE_COL = "payment_date";
    public static final String BALANCE_COL = "balance";


    static Statement statement = null;
    static Connection conn = null;
    static ResultSet rsConsignors = null;
    static ResultSet rsRecords = null;
    static ResultSet rsSales = null;
    static ResultSet rsInvoices = null;

    public static consignorDataModel cDM;
    public static recordDataModel rDM;
    public static saleDataModel sDM;
    public static invoiceDataModel iDM;


    DB() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class; check you have drives and classpath configured correctly?");
            cnfe.printStackTrace();
            System.exit(-1);  //No driver? Need to fix before anything else will work. So quit the program
        }

        //create the connection and statement object for running queries
        try {
            conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException se) {
            se.printStackTrace();
        }

        createTables();
        loadConsignors();
        loadRecords();
        loadSales();
        loadInvoices();

    }

    private void createTables() {

        try {
            //You should have already created a database via terminal/command prompt

            //Create a table in the database, if it does not exist already
            //Can use String formatting to build this type of String from constants coded in your program
            //Don't do this with variables with data from the user!! That's what ParameterisedStatements are, and that's for queries, updates etc. , not creating tables.
            // You shouldn't make database schemas from user input anyway.
            String createConsignorsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s VARCHAR (50), %s VARCHAR (25), PRIMARY KEY (%s))";
            String createConsignorsTableSQL = String.format(createConsignorsTableSQLTemplate, C_TABLE, CID_COL, NAME_COL, PHONE_COL, CID_COL);

            String createRecordsTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s int, %s VARCHAR (50), %s VARCHAR (50), %s decimal (10,2), %s date, %s int, %s VARCHAR (1), %s int, PRIMARY KEY (%s))";
            String createRecordsTableSQL = String.format(createRecordsTableSQLTemplate, R_TABLE, RID_COL, CID_COL, ARTIST_COL, TITLE_COL, SALESPRICE_COL, DATEADDED_COL, STATUS_COL, NOTIFIED_COL, SOLD_COL, RID_COL);

            String createSalesTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int not NULL AUTO_INCREMENT, %s int, %s decimal (10,2), %s decimal (10,2), PRIMARY KEY (%s))";
            String createSalesTableSQL = String.format(createSalesTableSQLTemplate, S_TABLE, SID_COL, RID_COL, SOLDDATE_COL, SOLDPRICE_COL, SID_COL);

            String createInvoicesTableSQLTemplate = "CREATE TABLE IF NOT EXISTS %s (%s int, %s int, %s decimal (10,2), %s decimal (10,2), %s decimal (10,2), %s date, %s decimal (10,2))";
            String createInvoicesTableSQL = String.format(createInvoicesTableSQLTemplate, I_TABLE, SID_COL, CID_COL, CIDPROFIT_COL, STOREPROFIT_COL, AMOUNTPAID_COL, PAYMENTDATE_COL, BALANCE_COL);

            statement.executeUpdate(createConsignorsTableSQL);
            System.out.println("Created consignors table");
            statement.executeUpdate(createRecordsTableSQL);
            System.out.println("Created records table");
            statement.executeUpdate(createSalesTableSQL);
            System.out.println("Created sales table");
            statement.executeUpdate(createInvoicesTableSQL);
            System.out.println("Created invoices table");

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    //load consignors from database, and update data model with results
    public static boolean loadConsignors() {
        try {

            if (rsConsignors != null) {
                rsConsignors.close();
            }

            String getAllData = "SELECT * FROM " + C_TABLE ;
            rsConsignors = statement.executeQuery(getAllData);

            if (cDM == null) {
                //If no current consignorDataModel, then make one
                cDM = new consignorDataModel(rsConsignors);
            } else {
                //Or, if one already exists, update its ResultSet
                cDM.updateResultSet(rsConsignors);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    //load records from database, and update data model with results
    public static boolean loadRecords() {
        try {

            if (rsRecords != null) {
                rsRecords.close();
            }

            String getAllData = "SELECT * FROM " + R_TABLE;
            rsRecords = statement.executeQuery(getAllData);

            if (rsRecords == null) {
                //If no current recordDataModel, then make one
                rDM = new recordDataModel(rsRecords);
            } else {
                //Or, if one already exists, update its ResultSet
                rDM.updateResultSet(rsRecords);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading records");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }


    }

    public static boolean loadSales() {
        try {

            if (rsSales != null) {
                rsSales.close();
            }

            String getAllData = "SELECT * FROM " + S_TABLE;
            rsSales = statement.executeQuery(getAllData);

            if (sDM == null) {
                //If no current salesDataModel, then make one
                sDM = new saleDataModel(rsSales);
            } else {
                //Or, if one already exists, update its ResultSet
                sDM.updateResultSet(rsSales);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading sales");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loadInvoices() {
        try {

            if (rsInvoices != null) {
                rsInvoices.close();
            }

            String getAllData = "SELECT * FROM " + I_TABLE;
            rsInvoices = statement.executeQuery(getAllData);

            if (iDM == null) {
                //If no current invoiceDataModel, then make one
                iDM = new invoiceDataModel(rsInvoices);
            } else {
                //Or, if one already exists, update its ResultSet
                iDM.updateResultSet(rsInvoices);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error loading or reloading invoices");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }


    public static boolean searchConsignors(String search){
        try{

            if (rsConsignors!=null) {
                rsConsignors.close();
            }

            String searchSQLTemplate = "SELECT * FROM %s WHERE %s LIKE ? OR %s LIKE ?";
            String searchSQL = String.format(searchSQLTemplate, C_TABLE, NAME_COL, PHONE_COL);
            System.out.println("The SQL for the prepared statement is " + searchSQL);
            PreparedStatement psSearch = conn.prepareStatement(searchSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            psSearch.setString(1, "%" + search + "%");
            psSearch.setString(2, "%" + search + "%");
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(psSearch.toString());

            rsConsignors = psSearch.executeQuery();

            if (cDM == null) {
                //create new consignorDataModel if it doesn't exist
                cDM = new consignorDataModel(rsConsignors);
            } else {
                //Or, if one already exists, update its ResultSet
                cDM.updateResultSet(rsConsignors);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error searching for consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean searchRecords(String search){
        try{

            if (rsRecords!=null) {
                rsRecords.close();
            }

            String searchSQLTemplate = "SELECT * FROM %s WHERE %s LIKE ? OR %s LIKE ?";
            String searchSQL = String.format(searchSQLTemplate, R_TABLE, ARTIST_COL, TITLE_COL);
            System.out.println("The SQL for the prepared statement is " + searchSQL);
            PreparedStatement psSearch = conn.prepareStatement(searchSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            psSearch.setString(1, "%" + search + "%");
            psSearch.setString(2, "%" + search + "%");
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(psSearch.toString());

            rsRecords = psSearch.executeQuery();

            if (rDM == null) {
                //create new recordDataModel if it doesn't exist
                rDM = new recordDataModel(rsRecords);
            } else {
                //Or, if one already exists, update its ResultSet
                rDM.updateResultSet(rsRecords);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error searching for consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean searchSales(int search){
        try{

            if (rsSales!=null) {
                rsSales.close();
            }

            String searchSQLTemplate = "SELECT * FROM %s WHERE %s = ?";
            String searchSQL = String.format(searchSQLTemplate, S_TABLE, RID_COL);
            System.out.println("The SQL for the prepared statement is " + searchSQL);
            PreparedStatement psSearch = conn.prepareStatement(searchSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            psSearch.setInt(1, search);
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(psSearch.toString());

            rsSales = psSearch.executeQuery();

            if (sDM == null) {
                //create new salesDataMode if it doesn't exist
                sDM = new saleDataModel(rsSales);
            } else {
                //Or, if one already exists, update its ResultSet
                sDM.updateResultSet(rsSales);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error searching for consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static boolean searchInvoices(int search){
        try{

            if (rsInvoices!=null) {
                rsInvoices.close();
            }

            String searchSQLTemplate = "SELECT * FROM %s WHERE %s = ?";
            String searchSQL = String.format(searchSQLTemplate, I_TABLE, CID_COL);
            System.out.println("The SQL for the prepared statement is " + searchSQL);
            PreparedStatement psSearch = conn.prepareStatement(searchSQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            psSearch.setInt(1, search);
            //For debugging - displays the actual SQL created in the PreparedStatement after the data has been set
            System.out.println(psSearch.toString());

            rsInvoices = psSearch.executeQuery();

            if (iDM == null) {
                //create new invoiceDataModel if it doesn't exist
                iDM = new invoiceDataModel(rsInvoices);
            } else {
                //Or, if one already exists, update its ResultSet
                iDM.updateResultSet(rsInvoices);
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error searching for consignors");
            System.out.println(e);
            e.printStackTrace();
            return false;
        }
    }

    public static void shutdown() {
        try {
            if (rsConsignors != null) {
                rsConsignors.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        try {
            if (rsRecords != null) {
                rsRecords.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        try {
            if (rsSales != null) {
                rsSales.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        try {
            if (rsInvoices != null) {
                rsInvoices.close();
                System.out.println("Result set closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed");
            }
        } catch (SQLException se){
            //Closing the connection could throw an exception too
            se.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

}
