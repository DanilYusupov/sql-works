import com.sqlworks.dao.ConnectionToDB;
import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EngDaoTest {
    private final String firstName = "Ivan";
    private final String lastName = "Ivanov";
    private final String major = "structure";
    private final String tel = "89001234567";
    private final long userId = 0;
    private final String tableName = "crud.TEST_ENGINEER_TABLE";
    private final Engineer engineer = new Engineer(firstName, lastName, major, tel);
    private final EngineerDao dao = new EngineerDao(tableName);

    @Before
    public void createTestTable() {
        try (Connection connection = ConnectionToDB.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table " + tableName + " (" +
                    "    id SERIAL PRIMARY KEY," +
                    "    firstName TEXT NOT NULL," +
                    "    lastName TEXT NOT NULL," +
                    "    major TEXT," +
                    "    tel TEXT);");
        } catch (SQLException e) {
            throw new DaoException("Creating " + tableName + " failed.");
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

    @After
    public void deleteTable() {
        try (Connection connection = ConnectionToDB.connect()) {

            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE " + tableName + ";");

        } catch (SQLException e) {
            throw new DaoException("Cannot drop table '" + tableName + "'", e);
        }
    }
}
