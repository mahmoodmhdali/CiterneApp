package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassTypeDao;
import com.citerneApp.project.model.EventClassType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassTypeService")
@Transactional
public class EventClassTypeServiceImpl extends AbstractService implements EventClassTypeService {

    @Autowired
    EventClassTypeDao eventClassTypeDao;

    @Override
    public List<EventClassType> getEventClassTypees() {
        return eventClassTypeDao.getEventClassTypees();
    }

    @Override
    public EventClassType getEventClassType(Long id) {
        return eventClassTypeDao.getEventClassType(id);
    }

}
