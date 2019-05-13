package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.EventClassCategory;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("eventClassCategoryDao")
public class EventClassCategoryDaoImpl extends AbstractDao<Long, EventClassCategory> implements EventClassCategoryDao {

    @Override
    public List<EventClassCategory> getEventClassCategoryes() {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.isNull("deletedDate"));
            List<EventClassCategory> eventClassCategoryes = (List<EventClassCategory>) criteria.list();
            return eventClassCategoryes;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCategoryDaoImpl 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public EventClassCategory getEventClassCategory(Long id) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.add(Restrictions.eq("id", id));
            EventClassCategory eventClassCategory = (EventClassCategory) criteria.uniqueResult();
            return eventClassCategory;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassCategoryDaoImpl 1 on API [" + ex.getMessage() + "]", "id= " + id, "");
        }
        return null;
    }

}
