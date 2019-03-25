package com.citerneApp.project.dao;

import com.citerneApp.project.model.Language;
import com.citerneApp.project.model.NotificationEvents;
import com.citerneApp.project.model.NotificationTexts;

public interface NotificationTextsDao {

    NotificationTexts get(NotificationEvents event, Language language);
}
