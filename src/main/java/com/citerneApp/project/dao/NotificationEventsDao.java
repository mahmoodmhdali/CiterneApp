package com.citerneApp.project.dao;

import com.citerneApp.project.model.NotificationEvents;
import com.citerneApp.project.model.UserProfile;
import java.util.List;

public interface NotificationEventsDao {

    NotificationEvents get(String eventName);

    List<NotificationEvents> getByUser(UserProfile user);
    
    NotificationEvents get(Long id);

    List<NotificationEvents> getAll();

    List<NotificationEvents> getEnabled();

    List<NotificationEvents> getAllWithTexts();

    List<NotificationEvents> getEnabledWithTextsAndUsers();

}
