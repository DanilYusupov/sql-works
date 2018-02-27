package com.sqlworks.service;

import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class EngineerService {
    //TODO: make embedded psql launch while flyway goals!
    private final EngineerDao dao;

    public EngineerService() {
        dao = new EngineerDao(getDataSource(), getTableName());
    }

    public EngineerDao getDao() {
        return dao;
    }

    public String getTableName() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/db.properties"));
            return properties.getProperty("dao.engineer.tablename");
        } catch (IOException e) {
            throw new DaoException("Cannot read table name from 'dao.project.tablename " +
                    "from file hikari.properties", e);
        }
    }

    public boolean deleteByName(String fullName) {
        String[] parts = fullName.split("\\s+");
        if (parts.length > 1) {
            return dao.deleteByName(parts[0], parts[1]);
        } else {
            return false;
        }
    }

    public Engineer getByName(String fullName) {
        String[] parts = fullName.split("\\s+");
        if (parts.length > 1) {
            return dao.getByName(parts[0], parts[1]);
        } else {
            return null;
        }
    }

    public String updateEngineer(Long id, String firstName, String lastName, String major, Long tel) {
        Engineer old = dao.getById(id);
        if (!firstName.equals("")){
            old.setFirstName(firstName);
        }
        if (!lastName.equals("")){
            old.setLastName(lastName);
        }
        if (!major.equals("")){
            old.setMajor(major);
        }
        if (tel != 0){
            old.setTel(tel);
        }
        dao.save(old);
        return "Engineer with id=" + id + " updated as " + old.getFirstName() + " " + old.getLastName();
    }

    public String getInfo(String fullName) {
        Engineer engineer = getByName(fullName);
        if (engineer != null) {
            return "<p>Engineer id: " + engineer.getId() + "</p> " +
                    "<p>Name: " + engineer.getFirstName() + " " + engineer.getLastName() + "</p> " +
                    "<p>Major: " + engineer.getMajor() + "</p> " +
                    "<p>Phone: " + engineer.getTel() + "</p>";
        } else {
            return "No such engineer!!!";
        }
    }

    private DataSource getDataSource(){
        HikariConfig config = new HikariConfig("/hikari.properties");
        return new HikariDataSource(config);
    }
}
