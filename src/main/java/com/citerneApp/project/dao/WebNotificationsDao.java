package com.citerneApp.project.dao;

import com.citerneApp.project.model.WebNotifications;
import java.util.List;

public interface WebNotificationsDao {

    List<WebNotifications> getAll(long userID, boolean all);

    void updateNotSeen(long userID);

    void add(WebNotifications webNotification);
    
    Integer getCountAll(long userID);

}
