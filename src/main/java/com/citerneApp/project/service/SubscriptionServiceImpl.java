package com.citerneApp.project.service;

import com.citerneApp.project.dao.SubscriptionDao;
import com.citerneApp.project.model.Subscription;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("subscriptionService")
@Transactional
public class SubscriptionServiceImpl extends AbstractService implements SubscriptionService {

    @Autowired
    SubscriptionDao subscriptionDao;

    @Override
    public List<Subscription> getSubscriptiones() {
        return subscriptionDao.getSubscriptiones();
    }

    @Override
    public Subscription getSubscription(Long id) {
        return subscriptionDao.getSubscription(id);
    }

}
