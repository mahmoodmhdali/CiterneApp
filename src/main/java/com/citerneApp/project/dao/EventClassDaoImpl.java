package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.helpermodel.HomePageEventsProfiles;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.EventClassCastAndCredit;
import com.citerneApp.project.model.EventClassMedia;
import com.citerneApp.project.model.EventClassSchedule;
import java.util.HashMap;
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
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.StringType;
import org.hibernate.type.TimeType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

@Repository("eventClassDao")
public class EventClassDaoImpl extends AbstractDao<Long, EventClass> implements EventClassDao {

    @Override
    public List<HomePageEvents> getHomePageEventClasses() {
//        Query query = createSqlQuery("SELECT a.id as id, a.title as title, a.ticketingURL as ticketingURL, a.categoryName as categoryName,a.duration as duration, t.name as typeName, i.path as mainImage, c.name as countryName,\n"
//                + "s.CLASS_DAY_INDEX as classDayIndex, s.TIME as time, s.SHOW_DATETIME as showDateTime, a.ind as ind\n"
//                + "from (\n"
//                + "SELECT T.ID as id, T.TITLE as title, T.TICKETING_URL as ticketingURL, c.name as categoryName,T.DURATION as duration, T.TYPE as type, T.COUNTRY as country, T.EVENT_INDEX as ind\n"
//                + "FROM ( SELECT * ,\n"
//                + "@num \\:= IF(@category= p.category, @num + 1, 1) AS row_number,\n"
//                + "@category \\:= p.category AS dummy\n"
//                + "FROM tbl_event_class p,\n"
//                + "(SELECT @num \\:= 0, @category\\:= '') d\n"
//                + "ORDER BY p.category, p.created_date DESC ) T\n"
//                + "INNER JOIN tbl_event_class_category c ON T.category=c.id\n"
//                + "WHERE row_number<=80\n"
//                + ") a \n"
//                + "INNER JOIN tbl_event_class_type t ON a.type=t.id\n"
//                + "INNER JOIN tbl_event_class_country c ON a.country=c.id\n"
//                + "INNER JOIN tbl_event_class_image i ON a.id=i.event_class and i.image_index = 1 \n"
//                + "INNER JOIN tbl_event_class_schedule s ON a.id=s.event_class\n"
//                + "order by a.ind")
//                .addScalar("id", new LongType())
//                .addScalar("title", new StringType())
//                .addScalar("ticketingURL", new StringType())
//                .addScalar("categoryName", new StringType())
//                .addScalar("duration", new IntegerType())
//                .addScalar("typeName", new StringType())
//                .addScalar("mainImage", new StringType())
//                .addScalar("countryName", new StringType())
//                .addScalar("classDayIndex", new IntegerType())
//                .addScalar("time", new DateType())
//                .addScalar("showDateTime", StandardBasicTypes.TIMESTAMP)
//                .addScalar("ind", new IntegerType())
//                .setResultTransformer(Transformers.aliasToBean(HomePageEvents.class));
        Query query = createSqlQuery("SELECT a.id as id, a.title as title, a.ticketing_URL as ticketingURL, cat.name as categoryName,a.duration as duration, t.name as typeName, i.path as mainImage, c.name as countryName,\n"
                + " s.CLASS_DAY_INDEX as classDayIndex, s.TIME as time, s.SHOW_DATETIME as showDateTime, a.event_index as ind\n"
                + " from tbl_event_class as a\n"
                + " INNER JOIN tbl_event_class_category cat ON a.category=cat.id\n"
                + " INNER JOIN tbl_event_class_type t ON a.type=t.id\n"
                + " INNER JOIN tbl_event_class_country c ON a.country=c.id\n"
                + " INNER JOIN tbl_event_class_image i ON a.id=i.event_class and i.image_index = 1 \n"
                + " INNER JOIN tbl_event_class_schedule s ON a.id=s.event_class\n"
                + " order by a.event_index")
                .addScalar("id", new LongType())
                .addScalar("title", new StringType())
                .addScalar("ticketingURL", new StringType())
                .addScalar("categoryName", new StringType())
                .addScalar("duration", new IntegerType())
                .addScalar("typeName", new StringType())
                .addScalar("mainImage", new StringType())
                .addScalar("countryName", new StringType())
                .addScalar("classDayIndex", new IntegerType())
                .addScalar("time", new DateType())
                .addScalar("showDateTime", StandardBasicTypes.TIMESTAMP)
                .addScalar("ind", new IntegerType())
                .setResultTransformer(Transformers.aliasToBean(HomePageEvents.class));
        return (List<HomePageEvents>) query.list();
    }

    @Override
    public HashMap<Long, String> getHomePageEventClassesProfiles() {
        HashMap<Long, String> hash = new HashMap<>();
        Query query = createSqlQuery("SELECT\n"
                + " tbl_event_class.ID as id,\n"
                + " GROUP_CONCAT(tbl_profile.name SEPARATOR ' & ') as profiles \n"
                + "FROM\n"
                + " tbl_event_class, tbl_profile, tbl_event_class_profiles\n"
                + "WHERE\n"
                + " tbl_event_class.ID = tbl_event_class_profiles.EVENT_CLASS_ID\n"
                + "AND\n"
                + " tbl_profile.ID = tbl_event_class_profiles.PROFILE_ID\n"
                + " GROUP BY \n"
                + "   tbl_event_class.ID")
                .addScalar("id", new LongType())
                .addScalar("profiles", new StringType())
                .setResultTransformer(Transformers.aliasToBean(HomePageEventsProfiles.class));
        List<HomePageEventsProfiles> homePageEventsProfiles = (List<HomePageEventsProfiles>) query.list();
        for (HomePageEventsProfiles homePageEventsProfile : homePageEventsProfiles) {
            hash.put(homePageEventsProfile.getId(), homePageEventsProfile.getProfiles());
        }
        return hash;
    }

    @Override
    public List<HomePageEvents> getHomePageEventClasses(String profileName) {
        Query query = createSqlQuery("SELECT a.id as id, a.title as title, a.ticketing_URL as ticketingURL, cat.name as categoryName,a.duration as duration, t.name as typeName, i.path as mainImage, c.name as countryName,\n"
                + " s.CLASS_DAY_INDEX as classDayIndex, s.TIME as time, s.SHOW_DATETIME as showDateTime, a.event_index as ind\n"
                + " from tbl_event_class as a\n"
                + " INNER JOIN tbl_event_class_category cat ON a.category=cat.id\n"
                + " INNER JOIN tbl_event_class_type t ON a.type=t.id\n"
                + " INNER JOIN tbl_event_class_country c ON a.country=c.id\n"
                + " INNER JOIN tbl_event_class_image i ON a.id=i.event_class and i.image_index = 1 \n"
                + " INNER JOIN tbl_event_class_schedule s ON a.id=s.event_class\n"
                + " INNER JOIN tbl_event_class_cast_and_credit cac ON a.id=cac.event_class\n"
                + " where cac.description LIKE ?\n"
                + " group by a.id,s.SHOW_DATETIME\n"
                + " order by a.event_index")
                .addScalar("id", new LongType())
                .addScalar("title", new StringType())
                .addScalar("ticketingURL", new StringType())
                .addScalar("categoryName", new StringType())
                .addScalar("duration", new IntegerType())
                .addScalar("typeName", new StringType())
                .addScalar("mainImage", new StringType())
                .addScalar("countryName", new StringType())
                .addScalar("classDayIndex", new IntegerType())
                .addScalar("time", new DateType())
                .addScalar("showDateTime", StandardBasicTypes.TIMESTAMP)
                .addScalar("ind", new IntegerType())
                .setResultTransformer(Transformers.aliasToBean(HomePageEvents.class))
                .setParameter(0, "%" + profileName + "%");
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
        criteria.createAlias("profileCollection", "profile");
        criteria.add(Restrictions.eq("profile.id", profileID));
        List<EventClass> eventClasses = (List<EventClass>) criteria.list();
        for (EventClass eventClass : eventClasses) {
            Hibernate.initialize(eventClass.getEventClassImages());
            Hibernate.initialize(eventClass.getEventClassSchedules());
            Hibernate.initialize(eventClass.getProfileCollection());
        }
        return eventClasses;
    }

    @Override
    public EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.createAlias("profileCollection", "profile");
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
            Hibernate.initialize(eventClass.getProfileCollection());
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
        Hibernate.initialize(eventClass.getProfileCollection());
        return eventClass;
    }

    @Override
    public EventClass addEventClass(EventClass eventClass) {
        try {
            for (EventClassSchedule eventClassSchedule : eventClass.getEventClassSchedules()) {
                eventClassSchedule.setEventClass(eventClass);
            }
            for (EventClassCastAndCredit eventClassCastAndCredit : eventClass.getEventClassCastAndCredits()) {
                eventClassCastAndCredit.setEventClass(eventClass);
            }
            for (EventClassMedia eventClassMedia : eventClass.getEventClassMedias()) {
                eventClassMedia.setEventClass(eventClass);
            }
            persist(eventClass);
        } catch (Exception ex) {
            Logger.ERROR("1- Error AdminPassesDao 4 on API [" + ex.getMessage() + "]", eventClass, "");
        }
        return eventClass;
    }

}
