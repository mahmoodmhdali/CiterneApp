package com.citerneApp.project.dao;

import com.citerneApp.project.model.Subscription;
import java.util.List;

public interface SubscriptionDao {

    List<Subscription> getSubscriptiones();

    Subscription getSubscription(Long id);

    Subscription getSubscriptionByEmail(String email);

    Subscription addSubscription(Subscription subscription);

}
