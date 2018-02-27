import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class TestUtils  {
    private static final Logger log = Logger.getLogger(TestUtils.class);
    private EmbeddedPostgres epg;
    @Rule
    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

    @Before
    public void init() throws SQLException {
        try {
            epg = EmbeddedPostgres.start();
            Connection c = epg.getPostgresDatabase().getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM crud.crud.engineer;");
        } catch (IOException e) {
            log.error("Trying to close RSQL. Error starting PSQLDB: ", e);
            try {
                epg.close();
            } catch (IOException e1) {
                log.error("Tried to close PSQLDB in cause of starting error. Closing failed: ", e1);
            }
        }
    }

    @After
    public void close() throws IOException {
        epg.close();
    }

}
