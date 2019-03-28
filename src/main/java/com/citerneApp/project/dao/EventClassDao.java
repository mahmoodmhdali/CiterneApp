package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.model.EventClass;
import java.util.List;

public interface EventClassDao {
    
    List<HomePageEvents> getHomePageEventClasses();

    List<EventClass> getEventClasses();

    List<EventClass> getEventClassesByCategory(Long categoryID);

    List<EventClass> getEventClassesByProfile(Long profileID);

    List<EventClass> getEventClassesByType(Long typeID);

    EventClass getEventClass(Long id);

}
