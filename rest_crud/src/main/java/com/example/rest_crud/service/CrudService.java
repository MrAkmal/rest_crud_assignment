package com.example.rest_crud.service;

import java.util.List;

public interface CrudService<T, B> {

    List<T> findAll();

    T save(T t);

    void deleteById(B b);

    T findById(Integer id);
}

