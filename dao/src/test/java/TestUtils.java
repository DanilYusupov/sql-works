import org.junit.After;
import org.junit.Before;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V10;

public abstract class TestUtils  {
//    private static final Logger log = Logger.getLogger(TestUtils.class);
//    private EmbeddedPostgres epg;
//    @Rule
//    public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();
//
//    @Before
//    public void init() throws SQLException {
//        try {
//            epg = EmbeddedPostgres.start();
//            Connection c = epg.getPostgresDatabase().getConnection();
//            Statement st = c.createStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM crud.crud.engineer;");
//        } catch (IOException e) {
//            log.error("Trying to close RSQL. Error starting PSQLDB: ", e);
//            try {
//                epg.close();
//            } catch (IOException e1) {
//                log.error("Tried to close PSQLDB in cause of starting error. Closing failed: ", e1);
//            }
//        }
//    }
//
//    @After
//    public void close() throws IOException {
//        epg.close();
//    }


    private EmbeddedPostgres postgres;

    @Before
    public void init() throws IOException, SQLException {
//        try {
//            postgres = new EmbeddedPostgres();
//            postgres.start(EmbeddedPostgres.cachedRuntimeConfig(Paths.get("C:\\Users\\UsupovD\\.embedpostgresql\\postgresql-10.1-2-windows-x64-binaries.zip")));
//        } catch (NullPointerException e) {
            postgres = new EmbeddedPostgres(V10, "D:\\Java\\pgsql\\data");
            String url = postgres.start("localhost", 5432, "crud", "cruder", "p@ssw0rd");
            Connection conn = DriverManager.getConnection(url);
            conn.createStatement().execute("SELECT * FROM crud.test_engineer_table");
//        }
    }

    @After
    public void close() {
        postgres.stop();
    }

}
