package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassImageDao;
import com.citerneApp.project.model.EventClassImage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassImageService")
@Transactional
public class EventClassImageServiceImpl extends AbstractService implements EventClassImageService {

    @Autowired
    EventClassImageDao eventClassImageDao;

    @Override
    public List<EventClassImage> getEventClassImagees() {
        return eventClassImageDao.getEventClassImagees();
    }

    @Override
    public EventClassImage getEventClassImage(Long id) {
        return eventClassImageDao.getEventClassImage(id);
    }

}
