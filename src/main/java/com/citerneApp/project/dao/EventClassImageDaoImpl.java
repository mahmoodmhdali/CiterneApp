package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassImage;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassImageDao")
public class EventClassImageDaoImpl extends AbstractDao<Long, EventClassImage> implements EventClassImageDao {

    @Override
    public List<EventClassImage> getEventClassImagees() {
        Criteria criteria = createEntityCriteria();
        List<EventClassImage> eventClassImagees = (List<EventClassImage>) criteria.list();
        return eventClassImagees;
    }

    @Override
    public EventClassImage getEventClassImage(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        EventClassImage eventClassImage = (EventClassImage) criteria.uniqueResult();
        return eventClassImage;
    }

}
