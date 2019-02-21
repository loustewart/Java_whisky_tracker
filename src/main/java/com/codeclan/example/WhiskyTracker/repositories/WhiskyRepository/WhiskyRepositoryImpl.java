package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;


    @Transactional //tells Spring the hibernate method will be used to carry out methods on the database
    @Override
    public List<Whisky> findWhiskyByAgeAndDistilleryId(int age, long distilleryId) {

        List<Whisky> results = null;
        Session session = entityManager.unwrap(Session.class);

        Criteria criteria = null;
        try {
            criteria = session.createCriteria(Whisky.class);
            criteria.add(Restrictions.eq("distillery.id", distilleryId));
            criteria.add(Restrictions.eq("age", age));
        } catch (Exception e) {
            e.printStackTrace();
        }

        results = criteria.list();
        return results;
    }

    @Transactional
    @Override
    public List<Whisky> findWhiskyByRegion(String region) {
        List<Whisky> found = null;
        Session session = entityManager.unwrap(Session.class);

        Criteria criteria = null;
        try {
            criteria = session.createCriteria(Whisky.class);
            criteria.createAlias("distillery", "distilleryObject");
            // distillery just has the distilleryId, distilleryObject returns the whole object
            // need this distilleryObject as an Alias to creat the join between the classes

            criteria.add(Restrictions.eq("distilleryObject.region", region));
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        found = criteria.list();
        return found;
    }


}
