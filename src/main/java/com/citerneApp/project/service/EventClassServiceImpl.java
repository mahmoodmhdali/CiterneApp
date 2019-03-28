package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassDao;
import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.model.EventClass;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassService")
@Transactional
public class EventClassServiceImpl extends AbstractService implements EventClassService {

    @Autowired
    EventClassDao eventClassDao;

    public List<HomePageEvents> getHomePageEventClasses() {
        return eventClassDao.getHomePageEventClasses();
    }

    @Override
    public List<EventClass> getEventClasses() {
        return eventClassDao.getEventClasses();
    }

    @Override
    public EventClassPagination getEventClassesPagination(int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPagination(pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByCategory(Long categoryID) {
        return eventClassDao.getEventClassesByCategory(categoryID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByCategory(Long categoryID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByCategory(categoryID, pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByProfile(Long profileID) {
        return eventClassDao.getEventClassesByProfile(profileID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByProfile(profileID, pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByType(Long typeID) {
        return eventClassDao.getEventClassesByType(typeID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByType(Long typeID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByType(typeID, pageNumber, maxRes);
    }

    @Override
    public EventClass getEventClass(Long id) {
        return eventClassDao.getEventClass(id);
    }

}
