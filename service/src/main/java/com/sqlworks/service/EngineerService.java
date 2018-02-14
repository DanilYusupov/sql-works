package com.sqlworks.service;

import com.sqlworks.dao.DaoException;
import com.sqlworks.dao.EngineerDao;

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
        return dao.deleteByName(parts[1], parts[2]);
    }
}
