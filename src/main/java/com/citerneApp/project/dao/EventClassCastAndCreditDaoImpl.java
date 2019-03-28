package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCastAndCredit;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCastAndCreditDao")
public class EventClassCastAndCreditDaoImpl extends AbstractDao<Long, EventClassCastAndCredit> implements EventClassCastAndCreditDao {

    @Override
    public List<EventClassCastAndCredit> getEventClassCastAndCredites() {
        Criteria criteria = createEntityCriteria();
        List<EventClassCastAndCredit> eventClassCastAndCredites = (List<EventClassCastAndCredit>) criteria.list();
        return eventClassCastAndCredites;
    }

    @Override
    public EventClassCastAndCredit getEventClassCastAndCredit(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        EventClassCastAndCredit eventClassCastAndCredit = (EventClassCastAndCredit) criteria.uniqueResult();
        return eventClassCastAndCredit;
    }

}
