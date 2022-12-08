package com.rezvatkin.testwork.dao;


import com.rezvatkin.testwork.entity.Division;

import java.util.List;

public interface DivisionDao {
    List<Division> findAll();

    Division findById(int id);

    void save(Division division);

    void deleteById(int id);

    void update(Division division);

    List<Division> findAllByParenId(int parentId);
}
