package com.citerneApp.project.service;

import com.citerneApp.project.model.Subscription;
import java.util.List;

public interface SubscriptionService {

    List<Subscription> getSubscriptiones();

    Subscription getSubscription(Long id);

}
