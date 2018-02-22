import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.sqlworks.dao.ConnectionToDB;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Test
    public void connectingTest() throws SQLException {
        try(Connection connection = ConnectionToDB.connect()){
            System.out.println("Connected!");
        }
    }
}