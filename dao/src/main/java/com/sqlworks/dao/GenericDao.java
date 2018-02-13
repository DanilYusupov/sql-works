package com.sqlworks.dao;

import java.util.List;

public interface GenericDao<T, V> {

    T getById(V id);

    T getByName(String firstName, String lastName);

    List<T> getAll();

    boolean deleteById(V id);

    V save(T entity);

}
