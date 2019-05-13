package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.EventClassSchedule;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassScheduleDao")
public class EventClassScheduleDaoImpl extends AbstractDao<Long, EventClassSchedule> implements EventClassScheduleDao {

    @Override
    public List<EventClassSchedule> getEventClassSchedulees() {
        Criteria criteria = createEntityCriteria();
        List<EventClassSchedule> eventClassSchedulees = (List<EventClassSchedule>) criteria.list();
        return eventClassSchedulees;
    }

    @Override
    public EventClassSchedule getEventClassSchedule(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        EventClassSchedule eventClassSchedule = (EventClassSchedule) criteria.uniqueResult();
        return eventClassSchedule;
    }

    @Override
    public void deleteEventClassSchedule(Long eventID) {
        try {
            Integer total = createSqlQuery("delete from tbl_event_class_schedule where event_class = " + eventID + "").executeUpdate();
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassScheduleDaoImpl 1 on API [" + ex.getMessage() + "]", "eventID= " + eventID, "");
        }
    }

}
