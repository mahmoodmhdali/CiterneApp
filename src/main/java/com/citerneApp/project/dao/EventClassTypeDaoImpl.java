package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassTypeDao")
public class EventClassTypeDaoImpl extends AbstractDao<Long, EventClassType> implements EventClassTypeDao {

    @Override
    public List<EventClassType> getEventClassTypees() {
        Criteria criteria = createEntityCriteria();
        List<EventClassType> eventClassTypees = (List<EventClassType>) criteria.list();
        return eventClassTypees;
    }

    @Override
    public EventClassType getEventClassType(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        EventClassType eventClassType = (EventClassType) criteria.uniqueResult();
        return eventClassType;
    }

}
