import com.opentable.db.postgres.embedded.FlywayPreparer;
import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.PreparedDbRule;
import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.junit.Assert.*;

public class EngDaoTest {

    private String firstName = "Ivan";
    private String lastName = "Ivanov";
    private String major = "structure";
    private Long tel = 89001234567L;
    private long userId = 0;
    private Engineer engineer = new Engineer(firstName, lastName, major, tel);

    /**
     * Check, that table name in '/db.properties' matches with table name in '/db/V1__Create-test-table.sql' !
     */
    private String tableName;

    public EngDaoTest() {
        setTableName();
    }

    @Rule
    public PreparedDbRule db = EmbeddedPostgresRules.preparedDatabase(FlywayPreparer.forClasspathLocation("db"));

    @Test
    public void testCrateUser(){
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
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
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
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
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
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
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
        long id = dao.save(engineer);
        dao.deleteById(id);

        assertNull(dao.getById(id));
    }

    @Test
    public void testCreatingExistentUser() {
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
        long id = dao.save(engineer);
        dao.save(engineer);

        dao.deleteById(id);
        assertNull(dao.getById(id));
    }

    @Test
    public void testGettingNonexistentUser() {
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
        try {
            dao.getById(userId);
        } catch (DaoException e) {
            assertEquals("Error getting engineer with ID=" + userId, e.getMessage());
        }

    }

    @Test
    public void testDeletingNonexistentUser() {
        EngineerDao dao = new EngineerDao(db.getTestDatabase(), tableName);
        try {
            dao.deleteById(userId);
        } catch (DaoException e) {
            assertEquals("Error removing entity with ID = " + userId, e.getMessage());
        }
    }

    private void setTableName() {
        Properties properties = new Properties();
        try {
            properties.load(EngDaoTest.class.getResourceAsStream("/db.properties"));
            this.tableName = properties.getProperty("table.name");
        } catch (IOException e) {
            throw new DaoException("Cannot get name for tests table: ", e);
        }
    }
}
