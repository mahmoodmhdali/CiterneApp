package com.citerneApp.project.service;

import com.citerneApp.project.dao.NotificationTextsDao;
import com.citerneApp.project.model.Language;
import com.citerneApp.project.model.NotificationEvents;
import com.citerneApp.project.model.NotificationTexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("notificationTextsService")
@Transactional
public class NotificationTextsServiceImpl implements NotificationTextsService {

    @Autowired
    private NotificationTextsDao notificationTextsDAO;

    @Override
    public NotificationTexts get(NotificationEvents event, Language language) {
        // TODO Auto-generated method stub
        return notificationTextsDAO.get(event, language);
    }

}
