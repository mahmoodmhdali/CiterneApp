package com.citerneApp.project.dao;

import com.citerneApp.project.model.Subscription;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("subscriptionDao")
public class SubscriptionDaoImpl extends AbstractDao<Long, Subscription> implements SubscriptionDao {

    @Override
    public List<Subscription> getSubscriptiones() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        List<Subscription> subscriptiones = (List<Subscription>) criteria.list();
        return subscriptiones;
    }

    @Override
    public Subscription getSubscription(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        Subscription subscription = (Subscription) criteria.uniqueResult();
        return subscription;
    }

    @Override
    public Subscription getSubscriptionByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        Subscription subscription = (Subscription) criteria.uniqueResult();
        return subscription;
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        persist(subscription);
        return subscription;
    }

}
