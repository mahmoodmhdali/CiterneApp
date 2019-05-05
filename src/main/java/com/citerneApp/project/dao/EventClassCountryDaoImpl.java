package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCountry;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCountryDao")
public class EventClassCountryDaoImpl extends AbstractDao<Long, EventClassCountry> implements EventClassCountryDao {

    @Override
    public List<EventClassCountry> getEventClassCountryes() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        List<EventClassCountry> eventClassCountryes = (List<EventClassCountry>) criteria.list();
        return eventClassCountryes;
    }

    @Override
    public EventClassCountry getEventClassCountry(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        EventClassCountry eventClassCountry = (EventClassCountry) criteria.uniqueResult();
        return eventClassCountry;
    }

}
