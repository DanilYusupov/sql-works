import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.sqlworks.dao.ConnectionToDB;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest extends TestUtils{

    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Test
    public void connectingTest() throws SQLException {
        try(Connection connection = ConnectionToDB.connect()){
            System.out.println("Connected!");
        }
    }

//    @Test
//    public void pgTest() throws IOException, SQLException {
//        try (EmbeddedPostgres epg = EmbeddedPostgres.start();
//             Connection c = epg.getPostgresDatabase().getConnection()){
//            Statement st = c.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM crud.public.flyway_schema_history");
//        }
//    }
}