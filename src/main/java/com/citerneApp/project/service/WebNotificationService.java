package com.citerneApp.project.service;

import com.citerneApp.project.model.WebNotifications;
import java.util.List;

public interface WebNotificationService {

    List<WebNotifications> getAll(long userID, boolean all);

    void updateNotSeen(long userID);

    void add(WebNotifications webNotification);
}
