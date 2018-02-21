import org.junit.After;
import org.junit.Before;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.yandex.qatools.embed.postgresql.distribution.Version.Main.V10;

public abstract class TestUtils {

    private EmbeddedPostgres postgres;

    @Before
    public void init() throws IOException, SQLException {
//        try {
//            postgres = new EmbeddedPostgres();
//            postgres.start(EmbeddedPostgres.cachedRuntimeConfig(Paths.get("C:\\Users\\Danil\\.embedpostgresql\\postgresql-10.1-2-windows-x64-binaries.zip")));
//        } catch (NullPointerException e) {
            postgres = new EmbeddedPostgres(V10);
          String url = postgres.start("localhost", 5432, "crud", "cruder", "p@ssw0rd");
//        }
        Connection conn = DriverManager.getConnection(url);
        conn.createStatement().execute("SELECT * FROM crud.test_engineer_table");
    }

    @After
    public void close(){
        postgres.stop();
    }

}
