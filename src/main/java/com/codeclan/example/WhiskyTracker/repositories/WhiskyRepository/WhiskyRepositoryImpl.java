package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Whisky> findWhiskyFromDistilleryWithYear(String name, int year) {
        List<Whisky> results = null;
        Criteria criteria = null;
    try {
        Session session = entityManager.unwrap(Session.class);
        criteria = session.createCriteria(Whisky.class);
        criteria.createAlias("distillery", "distillery");
        criteria.add(Restrictions.eq("distillery.name", name));
        criteria.add(Restrictions.eq("year", year));
    }
    catch (HibernateException e) {
        e.printStackTrace();
    }
    results = criteria.list();
    return results;
    }
}
