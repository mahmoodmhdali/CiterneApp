package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.Subscription;
import java.util.List;

public interface SubscriptionService {

    List<Subscription> getSubscriptiones();

    Subscription getSubscription(Long id);

    Subscription getSubscriptionByEmail(String email);

    ResponseBodyEntity addSubscription(Subscription subscription);

    ResponseBodyEntity deleteSubscription(String email);

}
