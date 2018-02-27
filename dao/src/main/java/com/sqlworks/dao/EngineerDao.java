package com.sqlworks.dao;

import com.sqlworks.model.Engineer;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngineerDao implements GenericDao<Engineer, Long> {

    private final DataSource ds;

    private String tableName;
    private static final Logger log = Logger.getLogger(EngineerDao.class);

    public EngineerDao(DataSource ds, String tableName) {
        this.ds = ds;
        this.tableName = tableName;
    }

    @Override
    public Engineer getByName(String firstName, String lastName) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE firstName = ? AND lastName = ?;");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet result = statement.executeQuery();
            return result.next() ? create(result) : null;
        } catch (SQLException e) {
            throw new DaoException("There is a problem with connection, or with query preparation, or there is" +
                    " no account with name: '" + firstName + " " + lastName + "' in table: '" + tableName + "'", e);
        }
    }

    @Override
    public Engineer getById(Long id) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE id = ?;");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            return result.next() ? create(result) : null;
        } catch (SQLException e) {
            throw new DaoException("Error getting user with ID=" + id, e);
        }

    }

    @Override
    public List<Engineer> getAll() {
        List<Engineer> userList = new ArrayList<>();
        try (Connection connection = ds.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM " + tableName + " ;");
            while (resultSet.next()) {
                userList.add(create(resultSet));
            }
            return userList.isEmpty() ? null : userList;
        } catch (SQLException e) {
            throw new DaoException("Cannot get all users in table '" + tableName + "':", e);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE id = ?;");
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException("Error removing entity with ID = " + id, e);
        }
    }

    public boolean deleteByName(String firstName, String lastName) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE firstName = ? AND lastName = ?;");
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException("Error removing engineer: " + firstName + " " + lastName, e);
        }
    }

    @Override
    public Long save(Engineer entity) {
        return (isNew(entity)) ? insert(entity) : update(entity);
    }

    protected Long insert(Engineer entity) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName +
                    " (firstName, lastName, major, tel) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getMajor());
            statement.setLong(4, entity.getTel());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                long userId = result.getLong(1);
                log.info("Created user with id: " + userId);
                return userId;
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create user '" + entity.getFirstName() + " " + entity.getLastName() + "'", e);
        }
        log.warn("Cannot return ID of user: '" + entity.getFirstName() + " " + entity.getLastName() + "'.");
        return null;
    }

    protected Long update(Engineer entity) {
        try (Connection connection = ds.getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE " + tableName +
                    " SET firstName = ?, lastName = ?, major = ?, tel = ? WHERE id = ?;");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getMajor());
            statement.setLong(4, entity.getTel());
            statement.setLong(5, entity.getId());
            int result = statement.executeUpdate();
            if (result > 0) {
                log.info("Updated user '" + entity.getFirstName() + " " + entity.getLastName() + "' with Id:"
                        + entity.getId());
                return entity.getId();
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot update user '" + entity.getFirstName() + " " + entity.getLastName()
                    + "' with Id: " + entity.getId() + " for table '" + tableName + "'.");
        }
        log.warn("Cannot return ID of user: '" + entity.getFirstName() + " " + entity.getLastName() + "'.");
        return null;
    }

    protected boolean isNew(Engineer entity) {
        return (entity.getId() == null);
    }

    private Engineer create(ResultSet result) throws SQLException {
        long id = result.getLong("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        String major = result.getString("major");
        Long tel = result.getLong("tel");
        return new Engineer(id, firstName, lastName, major, tel);
    }

    public DataSource getDatasource() {
        return ds;
    }

    public Engineer[] getCustom() {
        //TODO: realize method in accordance to checkbox!!!
        return null;
    }

}
