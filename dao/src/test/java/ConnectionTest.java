import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void connectingTest() throws SQLException {
        try(Connection connection = ConnectionToDB.connect()){
            System.out.println("Connected!");
        }
    }
}
