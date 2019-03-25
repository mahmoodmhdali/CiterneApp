package com.citerneApp.project.service;

import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.model.UserProfileNotificationEvent;
import java.util.List;

public interface UserProfileNotificationEventService {

    List<UserProfileNotificationEvent> getAll(UserProfile user);

    void deleteByUser(UserProfile user);

    List<UserProfileNotificationEvent> getAll();

    List<UserProfileNotificationEvent> updateUserNotifications(List<UserProfileNotificationEvent> userProfileNotificationEvents, UserProfile user);
}
