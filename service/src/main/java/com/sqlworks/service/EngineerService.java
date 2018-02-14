package com.sqlworks.service;

import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;
import com.sqlworks.model.Engineer;

import java.io.IOException;
import java.util.Properties;

public class EngineerService {

    private final EngineerDao dao = new EngineerDao(getTableName());

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
                    "from file db.properties", e);
        }
    }

    public boolean deleteByName(String fullName) {
        String[] parts = fullName.split("\\s+");
        return dao.deleteByName(parts[0], parts[1]);
    }

    public Engineer getByName(String fullName) {
        String[] parts = fullName.split("\\s+");
        return dao.getByName(parts[0], parts[1]);
    }

    public String updateEngineer(Long id, String firstName, String lastName, String major, String tel) {
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
        if (!tel.equals("")){
            old.setTel(tel);
        }
        dao.save(old);
        return "Engineer with id=" + id + " updated as " + old.getFirstName() + " " + old.getLastName();
    }

    public String getInfo(String fullName) {
        Engineer engineer = getByName(fullName);
        return "<p>Name: " + engineer.getFirstName() + " " + engineer.getLastName() + "</p> " +
                "<p>Major: " + engineer.getMajor() + "</p> " +
                "<p>Phone: " + engineer.getTel() + "</p>";
    }
}
