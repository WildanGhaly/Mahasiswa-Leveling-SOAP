package org.example.model;

import java.util.List;

public interface GenericDAO<T> {
    void insert(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
