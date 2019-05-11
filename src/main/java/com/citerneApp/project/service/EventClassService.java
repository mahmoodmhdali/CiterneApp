package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.Profile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface EventClassService {

    List<HomePageEvents> getHomePageEventClasses();

    HashMap<Long, String> getHomePageEventClassesProfiles();

    List<HomePageEvents> getHomePageEventClasses(String profileName);

    List<EventClass> getEventClasses();

    void getEventClassesForMidnightCheck();

    EventClassPagination getEventClassesPagination(int pageNumber, int maxRes);

    List<EventClass> getEventClassesByCategory(Long categoryID);

    EventClassPagination getEventClassesPaginationByCategory(Long categoryID, int pageNumber, int maxRes);

    List<EventClass> getEventClassesByProfile(Long profileID);

    EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes);

    List<EventClass> getEventClassesByType(Long typeID);

    EventClassPagination getEventClassesPaginationByType(Long typeID, int pageNumber, int maxRes);

    EventClass getEventClass(Long id);

    ResponseBodyEntity addEventClass(EventClass eventClass);

    ResponseBodyEntity deleteEventClass(Long eventClassID);

    ResponseBodyEntity editEventClass(EventClass eventClass);

    ResponseBodyEntity editEventClassImages(EventClass eventClass, MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException;

}
