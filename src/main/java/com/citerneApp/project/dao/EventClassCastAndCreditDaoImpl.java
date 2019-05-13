package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.EventClassCastAndCredit;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCastAndCreditDao")
public class EventClassCastAndCreditDaoImpl extends AbstractDao<Long, EventClassCastAndCredit> implements EventClassCastAndCreditDao {

    @Override
    public List<EventClassCastAndCredit> getEventClassCastAndCredites() {
        List<EventClassCastAndCredit> eventClassCastAndCredites = new ArrayList<>();
        try {
            Criteria criteria = createEntityCriteria();
            eventClassCastAndCredites = (List<EventClassCastAndCredit>) criteria.list();
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCastAndCreditDaoImpl 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return eventClassCastAndCredites;
    }

    @Override
    public EventClassCastAndCredit getEventClassCastAndCredit(Long id) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            EventClassCastAndCredit eventClassCastAndCredit = (EventClassCastAndCredit) criteria.uniqueResult();
            return eventClassCastAndCredit;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCastAndCreditDaoImpl 2 on API [" + ex.getMessage() + "]", "id= " + id, "");
        }
        return null;
    }

    @Override
    public void deleteEventClassCastAndCredit(Long eventID) {
        try {
            Integer total = createSqlQuery("delete from tbl_event_class_cast_and_credit where event_class = " + eventID + "").executeUpdate();
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCastAndCreditDaoImpl 3 on API [" + ex.getMessage() + "]", "eventID= " + eventID, "");
        }
    }

}
