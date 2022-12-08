package com.rezvatkin.testwork.dao;

import com.rezvatkin.testwork.entity.Division;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class DivisionDAOHibernateImpl implements DivisionDao {

    private EntityManager entityManager;

    @Override
    public List<Division> findAll() {
        var currentSession = entityManager.unwrap(Session.class);
        var theQuery = currentSession.createQuery("from Division where dtFrom <= now()", Division.class);
        var unlimitedDivision = theQuery
                .stream()
                .filter(division -> division.getDtTill() == null)
                .collect(Collectors.toList());
        var limitedDivision = theQuery
                .stream()
                .filter(division -> division.getDtTill() != null)
                .filter(division -> LocalDate.now().isBefore(division.getDtTill()))
                .collect(Collectors.toList());
        unlimitedDivision.addAll(limitedDivision);
        return unlimitedDivision;
    }

    @Override
    public Division findById(int id) {
        var currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Division.class, id);
    }

    @Override
    public void save(Division division) {
        var currentSession = entityManager.unwrap(Session.class);
        currentSession.persist(division);
    }

    @Override
    public void deleteById(int id) {
        var currentSession = entityManager.unwrap(Session.class);
        var theQuery = currentSession.createQuery(
                "delete from Division where id =:divisionId"
        );
        theQuery.setParameter("divisionId", id);
        theQuery.executeUpdate();
    }

    @Override
    public void update(Division division) {
        var currentSession = entityManager.unwrap(Session.class);
        currentSession.merge(division);
    }

    @Override
    public List<Division> findAllByParenId(int parentId) {
        var currentSession = entityManager.unwrap(Session.class);
        var theQuery = currentSession.createQuery(
                "from Division where parentId=:parentId",
                Division.class
        );
        theQuery.setParameter("parentId", parentId);

        return theQuery.getResultList();
    }
}
