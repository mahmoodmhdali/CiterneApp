package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.model.EventClass;
import java.util.HashMap;
import java.util.List;

public interface EventClassDao {

    List<HomePageEvents> getHomePageEventClasses();

    HashMap<Long, String> getHomePageEventClassesProfiles();

    List<HomePageEvents> getHomePageEventClasses(String profileName);

    List<EventClass> getEventClasses();

    List<EventClass> getEventClassesForMidnightCheck();

    EventClassPagination getEventClassesPagination(int pageNumber, int maxRes);

    List<EventClass> getEventClassesByCategory(Long categoryID);

    EventClassPagination getEventClassesPaginationByCategory(Long categoryID, int pageNumber, int maxRes);

    List<EventClass> getEventClassesByProfile(Long profileID);

    EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes);

    List<EventClass> getEventClassesByType(Long typeID);

    EventClassPagination getEventClassesPaginationByType(Long typeID, int pageNumber, int maxRes);

    EventClass getEventClass(Long id);

    EventClass addEventClass(EventClass eventClass);

    void deleteEventClassImages(Long eventID);
}
