package com.rezvatkin.testwork.service;

import com.rezvatkin.testwork.dao.DivisionDao;
import com.rezvatkin.testwork.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {

    private final DivisionDao divisionDao;

    @Autowired
    public DivisionServiceImpl(DivisionDao divisionDao) {
        this.divisionDao = divisionDao;
    }

    @Override
    @Transactional
    public List<Division> findAll() {
        return divisionDao.findAll();
    }

    @Override
    @Transactional
    public Division findById(int id) {
        return divisionDao.findById(id);
    }

    @Override
    @Transactional
    public void save(Division division) {
        divisionDao.save(division);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        divisionDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Division division) {
        division.setCorrectionDate(LocalDateTime.now());
        divisionDao.update(division);
    }

    @Override
    public List<Division> findAllByParentId(int parentId) {
        return divisionDao.findAllByParenId(parentId);
    }
}
