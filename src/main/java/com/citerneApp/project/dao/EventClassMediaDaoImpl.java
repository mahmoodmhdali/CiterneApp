package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassMedia;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassMediaDao")
public class EventClassMediaDaoImpl extends AbstractDao<Long, EventClassMedia> implements EventClassMediaDao {

    @Override
    public List<EventClassMedia> getEventClassMediaes() {
        Criteria criteria = createEntityCriteria();
        List<EventClassMedia> eventClassMediaes = (List<EventClassMedia>) criteria.list();
        return eventClassMediaes;
    }

    @Override
    public EventClassMedia getEventClassMedia(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        EventClassMedia eventClassMedia = (EventClassMedia) criteria.uniqueResult();
        return eventClassMedia;
    }

}
