package com.citerneApp.project.dao;

import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.model.UserProfileNotificationEvent;
import java.util.List;

public interface UserProfileNotificationEventDao {

    List<UserProfileNotificationEvent> getAll(UserProfile user);

    void deleteByUser(UserProfile user);

    List<UserProfileNotificationEvent> getAll();

}
