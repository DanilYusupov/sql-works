import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.sqlworks.dao.ConnectionToDB;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest {

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Test
    public void connectingTest() throws SQLException {
        try(Connection connection = pg.getEmbeddedPostgres().getPostgresDatabase().getConnection()){
            System.out.println("Connected on port: " + pg.getEmbeddedPostgres().getPort());
        }
    }
}