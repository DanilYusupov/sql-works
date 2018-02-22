import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;
import org.junit.Test;
import ru.yandex.qatools.embed.postgresql.EmbeddedPostgres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class EngDaoTest extends TestUtils{
    private final String firstName = "Ivan";
    private final String lastName = "Ivanov";
    private final String major = "structure";
    private final Long tel = 89001234567L;
    private final long userId = 0;
    private final String tableName = "crud.test_engineer_table";
    private final Engineer engineer = new Engineer(firstName, lastName, major, tel);
    private final EngineerDao dao = new EngineerDao(tableName);
    private EmbeddedPostgres postgres;

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
