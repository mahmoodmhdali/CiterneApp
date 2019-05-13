package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
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

    @Override
    public void deleteEventClassMedia(Long eventID) {
        try {
            Integer total = createSqlQuery("delete from tbl_event_class_media where event_class = " + eventID + "").executeUpdate();
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassMediaDaoImpl 1 on API [" + ex.getMessage() + "]", "eventID= " + eventID, "");
        }
    }

}
