package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassMediaDao;
import com.citerneApp.project.model.EventClassMedia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassMediaService")
@Transactional
public class EventClassMediaServiceImpl extends AbstractService implements EventClassMediaService {

    @Autowired
    EventClassMediaDao eventClassMediaDao;

    @Override
    public List<EventClassMedia> getEventClassMediaes() {
        return eventClassMediaDao.getEventClassMediaes();
    }

    @Override
    public EventClassMedia getEventClassMedia(Long id) {
        return eventClassMediaDao.getEventClassMedia(id);
    }

}
