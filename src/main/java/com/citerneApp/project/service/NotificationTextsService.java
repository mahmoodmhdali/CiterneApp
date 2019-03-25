package com.citerneApp.project.service;

import com.citerneApp.project.model.Language;
import com.citerneApp.project.model.NotificationEvents;
import com.citerneApp.project.model.NotificationTexts;

public interface NotificationTextsService {

    NotificationTexts get(NotificationEvents event, Language language);
}
