package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassCategoryDao;
import com.citerneApp.project.model.EventClassCategory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassCategoryService")
@Transactional
public class EventClassCategoryServiceImpl extends AbstractService implements EventClassCategoryService {

    @Autowired
    EventClassCategoryDao eventClassCategoryDao;

    @Override
    public List<EventClassCategory> getEventClassCategoryes() {
        return eventClassCategoryDao.getEventClassCategoryes();
    }

    @Override
    public EventClassCategory getEventClassCategory(Long id) {
        return eventClassCategoryDao.getEventClassCategory(id);
    }

}
