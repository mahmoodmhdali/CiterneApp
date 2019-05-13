package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
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

    @Override
    public EventClassImage addEventClassImage(EventClassImage eventClassImage) {
        try {
            Integer total = createSqlQuery("insert into tbl_event_class_image (name, path, image_index, event_class) values (:name, :path, :imageIndex, :eventID)")
                    .setParameter("name", eventClassImage.getFileName())
                    .setParameter("path", eventClassImage.getPath())
                    .setParameter("imageIndex", eventClassImage.getImageIndex())
                    .setParameter("eventID", eventClassImage.getEventClass().getId())
                    .executeUpdate();
            return eventClassImage;
        } catch (Exception ex) {
            Logger.ERROR("1- Error EventClassImageDaoImpl 1 on API [" + ex.getMessage() + "]", "eventClassImage= " + eventClassImage, "");
        }
        return null;
    }

}
