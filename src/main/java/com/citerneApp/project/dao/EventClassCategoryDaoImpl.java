package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCategory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCategoryDao")
public class EventClassCategoryDaoImpl extends AbstractDao<Long, EventClassCategory> implements EventClassCategoryDao {

    @Override
    public List<EventClassCategory> getEventClassCategoryes() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        List<EventClassCategory> eventClassCategoryes = (List<EventClassCategory>) criteria.list();
        return eventClassCategoryes;
    }

    @Override
    public EventClassCategory getEventClassCategory(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        EventClassCategory eventClassCategory = (EventClassCategory) criteria.uniqueResult();
        return eventClassCategory;
    }

}
