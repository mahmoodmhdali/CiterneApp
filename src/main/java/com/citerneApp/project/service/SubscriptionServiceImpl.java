package com.citerneApp.project.service;

import com.citerneApp.project.dao.SubscriptionDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.Subscription;
import java.util.Date;
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

    @Override
    public Subscription getSubscriptionByEmail(String email) {
        return subscriptionDao.getSubscriptionByEmail(email);
    }

    @Override
    public ResponseBodyEntity addSubscription(Subscription subscription) {
        Subscription persistSubscription = subscriptionDao.getSubscriptionByEmail(subscription.getEmail());
        if (persistSubscription == null) {
            persistSubscription = subscriptionDao.addSubscription(subscription);
        } else if (persistSubscription.getDeletedDate() != null) {
            persistSubscription.setDeletedDate(null);
        } else {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("email", "Email already exist")
                    .getResponse();
        }
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("subscription", persistSubscription)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity deleteSubscription(String email) {
        Subscription persistSubscription = subscriptionDao.getSubscriptionByEmail(email);
        if (persistSubscription == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("email", "Email does not exist")
                    .getResponse();
        } else if (persistSubscription.getDeletedDate() == null) {
            persistSubscription.setDeletedDate(new Date());
        }
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("subscription", persistSubscription)
                .getResponse();
    }

}
