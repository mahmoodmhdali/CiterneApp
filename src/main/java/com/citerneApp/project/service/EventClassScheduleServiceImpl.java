package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassScheduleDao;
import com.citerneApp.project.model.EventClassSchedule;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassScheduleService")
@Transactional
public class EventClassScheduleServiceImpl extends AbstractService implements EventClassScheduleService {

    @Autowired
    EventClassScheduleDao eventClassScheduleDao;

    @Override
    public List<EventClassSchedule> getEventClassSchedulees() {
        return eventClassScheduleDao.getEventClassSchedulees();
    }

    @Override
    public EventClassSchedule getEventClassSchedule(Long id) {
        return eventClassScheduleDao.getEventClassSchedule(id);
    }

}
