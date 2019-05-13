package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.EventClassCountry;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCountryDao")
public class EventClassCountryDaoImpl extends AbstractDao<Long, EventClassCountry> implements EventClassCountryDao {

    @Override
    public List<EventClassCountry> getEventClassCountryes() {
        try {
            Criteria criteria = createEntityCriteria();
            List<EventClassCountry> eventClassCountryes = (List<EventClassCountry>) criteria.list();
            return eventClassCountryes;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCountryDaoImpl 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public EventClassCountry getEventClassCountry(Long id) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("id", id));
            EventClassCountry eventClassCountry = (EventClassCountry) criteria.uniqueResult();
            return eventClassCountry;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCountryDaoImpl 2 on API [" + ex.getMessage() + "]", "id= " + id, "");
        }
        return null;
    }

}
