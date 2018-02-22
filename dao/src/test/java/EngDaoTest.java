import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EngDaoTest {
    //TODO: Попробуй решить задлачу от обратного! Верни на место ДАО и добавь перегруженный метод коннект, где на вход ты подаешь порт
    //TODO: а так как порт всегда рандомный, то его надо брать из embeddedDB!
    private final String firstName = "Ivan";
    private final String lastName = "Ivanov";
    private final String major = "structure";
    private final Long tel = 89001234567L;
    private final long userId = 0;
    private final String tableName = "crud.test_engineer_table";
    private final Engineer engineer = new Engineer(firstName, lastName, major, tel);
    private EngineerDao dao;

    @Rule
    public SingleInstancePostgresRule epg = EmbeddedPostgresRules.singleInstance();

    @Before
    public void init() throws SQLException {
        Map<String, String> props = new HashMap();
        props.put("PGPORT", "5432");
        dao = new EngineerDao(epg.getEmbeddedPostgres().getDatabase("cruder", "crud", props)
                .getConnection("cruder", "p@ssw0rd"), tableName);
    }

    @Test
    public void testRule() throws Exception {
        try (Connection c = epg.getEmbeddedPostgres().getPostgresDatabase().getConnection()){
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT 1");
            assertTrue(rs.next());
            assertEquals(1, rs.getInt(1));
            assertFalse(rs.next());
        }
    }

    @Test
    public void testCrateUser() {
        Long id = dao.save(engineer);
        assertNotNull(id);
        Engineer saved = dao.getById(id);
        assertEquals(firstName, saved.getFirstName());
        assertEquals(lastName, saved.getLastName());
        assertEquals(major, saved.getMajor());

        dao.deleteById(id);
    }

    @Test
    public void testUpdateUser() {
        String updateMajor = "Developer";
        long id = dao.save(engineer);
        Engineer toUpdate = dao.getById(id);
        toUpdate.setMajor(updateMajor);
        dao.save(toUpdate);
        assertEquals(updateMajor, dao.getById(id).getMajor());

        dao.deleteById(id);
    }

    @Test
    public void testGetUser() {
        long id = dao.save(engineer);
        Engineer user = dao.getById(id);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(tel, user.getTel());

        user = dao.getByName(firstName, lastName);
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(major, user.getMajor());

        dao.deleteById(id);
    }

    @Test
    public void testDeleteUser() {
        long id = dao.save(engineer);
        dao.deleteById(id);

        assertNull(dao.getById(id));
    }

    @Test
    public void testCreatingExistentUser() {
        long id = dao.save(engineer);
        dao.save(engineer);

        dao.deleteById(id);
        assertNull(dao.getById(id));
    }

    @Test
    public void testGettingNonexistentUser() {
        try {
            dao.getById(userId);
        } catch (DaoException e) {
            assertEquals("Error getting engineer with ID=" + userId, e.getMessage());
        }

    }

    @Test
    public void testDeletingNonexistentUser() {
        try {
            dao.deleteById(userId);
        } catch (DaoException e) {
            assertEquals("Error removing entity with ID = " + userId, e.getMessage());
        }
    }
}
