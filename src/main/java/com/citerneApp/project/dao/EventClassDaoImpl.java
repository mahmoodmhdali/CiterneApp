package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.model.EventClass;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

@Repository("eventClassDao")
public class EventClassDaoImpl extends AbstractDao<Long, EventClass> implements EventClassDao {

    @Override
    public List<HomePageEvents> getHomePageEventClasses() {
//        Query query = createSqlQuery("SELECT T.ID as id, T.TITLE as title, T.CATEGORY as category,T.TYPE as type,T.AUTHOR as author,"
//                + "T.ABOUT as about,T.CREATED_DATE as created_date,T.UPDATED_DATE as updated_date,T.DELETED_DATE as deleted_date,"
//                + "T.DURATION as duration,T.COUNTRY as country\n"
//                + "FROM ( SELECT * ,\n"
//                + "@num \\:= IF(@category= p.category, @num + 1, 1) AS row_number,\n"
//                + "@category \\:= p.category AS dummy\n"
//                + "FROM tbl_event_class p,\n"
//                + "(SELECT @num \\:= 0, @category\\:= '') d\n"
//                + "ORDER BY p.category, p.created_date DESC ) T\n"
//                + "INNER JOIN tbl_event_class_category c ON T.category=c.id\n"
//                + "WHERE row_number<=3")
//                .setResultTransformer(Transformers.aliasToBean(EventClass.class));
        Query query = createSqlQuery("SELECT a.id as id, a.title as title, a.ticketingURL as ticketingURL, a.categoryName as categoryName,a.duration as duration, p.name as profileName, t.name as typeName, i.path as mainImage, c.name as countryName,\n"
                + "s.CLASS_DAY_INDEX as classDayIndex, s.TIME as time, s.SHOW_DATETIME as showDateTime\n"
                + "from (\n"
                + "SELECT T.ID as id, T.TITLE as title, T.TICKETING_URL as ticketingURL, c.name as categoryName,T.DURATION as duration, T.AUTHOR as author, T.TYPE as type, T.COUNTRY as country\n"
                + "FROM ( SELECT * ,\n"
                + "@num \\:= IF(@category= p.category, @num + 1, 1) AS row_number,\n"
                + "@category \\:= p.category AS dummy\n"
                + "FROM tbl_event_class p,\n"
                + "(SELECT @num \\:= 0, @category\\:= '') d\n"
                + "ORDER BY p.category, p.created_date DESC ) T\n"
                + "INNER JOIN tbl_event_class_category c ON T.category=c.id\n"
                + "WHERE row_number<=3\n"
                + ") a \n"
                + "INNER JOIN tbl_profile p ON a.author=p.id\n"
                + "INNER JOIN tbl_event_class_type t ON a.type=t.id\n"
                + "INNER JOIN tbl_event_class_country c ON a.country=c.id\n"
                + "INNER JOIN tbl_event_class_image i ON a.id=i.event_class and i.image_index = 1 \n"
                + "INNER JOIN tbl_event_class_schedule s ON a.id=s.event_class\n"
                + "order by categoryName,title")
                .addScalar("id", new LongType())
                .addScalar("title", new StringType())
                .addScalar("ticketingURL", new StringType())
                .addScalar("categoryName", new StringType())
                .addScalar("duration", new IntegerType())
                .addScalar("profileName", new StringType())
                .addScalar("typeName", new StringType())
                .addScalar("mainImage", new StringType())
                .addScalar("countryName", new StringType())
                .addScalar("classDayIndex", new IntegerType())
                .addScalar("time", new DateType())
                .addScalar("showDateTime", new DateType())
                .setResultTransformer(Transformers.aliasToBean(HomePageEvents.class));
        return (List<HomePageEvents>) query.list();
    }

    @Override
    public List<EventClass> getEventClasses() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        return eventClasses;
    }

    @Override
    public EventClassPagination getEventClassesPagination(int pageNumber, int maxRes) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setProjection(Projections.rowCount());
        Number totalResults = (Number) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * maxRes);
        criteria.setMaxResults(maxRes);
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        int currentPage = pageNumber;
        int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
        EventClassPagination eventClassPagination = new EventClassPagination(maxPages, currentPage, totalResults.intValue(), eventClasses);
        return eventClassPagination;
    }

    @Override
    public List<EventClass> getEventClassesByCategory(Long categoryID) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("eventClassCategory", "category");
        criteria.add(Restrictions.eq("category.id", categoryID));
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        return eventClasses;
    }

    @Override
    public EventClassPagination getEventClassesPaginationByCategory(Long categoryID, int pageNumber, int maxRes) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("eventClassCategory", "category");
        criteria.add(Restrictions.eq("category.id", categoryID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
        criteria.setProjection(Projections.rowCount());
        Number totalResults = (Number) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * maxRes);
        criteria.setMaxResults(maxRes);
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        int currentPage = pageNumber;
        int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
        EventClassPagination eventClassPagination = new EventClassPagination(maxPages, currentPage, totalResults.intValue(), eventClasses);
        return eventClassPagination;
    }

    @Override
    public List<EventClass> getEventClassesByProfile(Long profileID) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("profile", "profile");
        criteria.add(Restrictions.eq("profile.id", profileID));
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        return eventClasses;
    }

    @Override
    public EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("profile", "profile");
        criteria.add(Restrictions.eq("profile.id", profileID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
        criteria.setProjection(Projections.rowCount());
        Number totalResults = (Number) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * maxRes);
        criteria.setMaxResults(maxRes);
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        int currentPage = pageNumber;
        int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
        EventClassPagination eventClassPagination = new EventClassPagination(maxPages, currentPage, totalResults.intValue(), eventClasses);
        return eventClassPagination;
    }

    @Override
    public List<EventClass> getEventClassesByType(Long typeID) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("eventClassType", "type");
        criteria.add(Restrictions.eq("type.id", typeID));
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        return eventClasses;
    }

    @Override
    public EventClassPagination getEventClassesPaginationByType(Long typeID, int pageNumber, int maxRes) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("eventClassType", "type");
        criteria.add(Restrictions.eq("type.id", typeID));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
        criteria.setProjection(Projections.rowCount());
        Number totalResults = (Number) criteria.uniqueResult();
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        criteria.setFirstResult((pageNumber - 1) * maxRes);
        criteria.setMaxResults(maxRes);
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
        }
        int currentPage = pageNumber;
        int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
        EventClassPagination eventClassPagination = new EventClassPagination(maxPages, currentPage, totalResults.intValue(), eventClasses);
        return eventClassPagination;
    }

    @Override
    public EventClass getEventClass(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        EventClass eventClass = (EventClass) criteria.uniqueResult();
        Hibernate.initialize(eventClass.getEventClassImages());
        Hibernate.initialize(eventClass.getEventClassSchedules());
        Hibernate.initialize(eventClass.getEventClassCastAndCredits());
        Hibernate.initialize(eventClass.getEventClassMedias());
        return eventClass;
    }

}
