package ro.teamnet.zth.api.database;

import java.sql.*;

import static ro.teamnet.zth.api.database.DBProperties.PASS;
import static ro.teamnet.zth.api.database.DBProperties.USER;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class DBManager {

    public static final String  CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT + ":xe";

    private DBManager() {
            throw new UnsupportedOperationException();
    }

    private static void registerDriver(){
        try{
            Class.forName(DBProperties.DRIVER_CLASS);
        }catch (ClassNotFoundException e){
            System.out.println("Error:unable to load diver class!");
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {
        Connection c1 = null;
        try {
            c1 = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c1;
    }

    public static boolean checkConnection(Connection connection){


        try {
            Statement stmt = connection.createStatement();
            boolean rez = stmt.execute("SELECT 1 FROM DUAL");
            return rez;

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }
}
