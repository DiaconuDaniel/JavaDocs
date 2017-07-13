package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Daniel.Diaconu on 17/07/13.
 */
public class DBManagerTest {


    @Test
    public void getTestConnection(){
        Connection connection = DBManager.getConnection();
        boolean rez = true;
        assertEquals(rez, DBManager.checkConnection(connection));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
