package com.rezvatkin.testwork.service;

import com.rezvatkin.testwork.entity.Division;

import java.util.List;

public interface DivisionService {
    List<Division> findAll();

    Division findById(int id);

    void save(Division division);

    void deleteById(int id);

    void update(Division division);

    List<Division> findAllByParentId(int parentId);
}
