package com.citerneApp.project.controller;

import com.citerneApp.project.helpermodel.HomePageEventSchedule;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.EventClassImage;
import com.citerneApp.project.service.EventClassService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventClasses")
public class EventClassController extends AbstractController {

    @Autowired
    EventClassService eventClassService;

    @GetMapping
    public ResponseEntity getEventClasses() {
        List<EventClass> eventClasses = eventClassService.getEventClasses();
        for (EventClass eventClass : eventClasses) {
            Set<EventClassImage> eventClassImages = new HashSet<>();
            for (EventClassImage eventClassImage : eventClass.getEventClassImages()) {
                if (eventClassImage.getImageIndex() == 1) {
                    eventClassImages.add(eventClassImage);
                }
            }
            eventClass.setEventClassImages(eventClassImages);
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("eventClasses", eventClasses)
                .returnClientResponse();
    }

    @GetMapping("/paging/{pageNumber}/{maxResult}")
    public ResponseEntity getEventClassesPagination(@PathVariable Integer pageNumber, @PathVariable Integer maxResult) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("passes", eventClassService.getEventClassesPagination(pageNumber, maxResult))
                .returnClientResponse();
    }

    @GetMapping("/home")
    public ResponseEntity getHomePageEventClasses() {
        List<HomePageEvents> formatedHomePageEvents = new ArrayList<>();
        List<HomePageEvents> homePageEvents = eventClassService.getHomePageEventClasses();
        HashMap<Long, String> hash = eventClassService.getHomePageEventClassesProfiles();
        for (HomePageEvents homePageEvent : homePageEvents) {
            boolean eventExist = false;
            homePageEvent.setProfileName(hash.get(homePageEvent.getId()));
            HomePageEventSchedule homePageEventSchedule = new HomePageEventSchedule();
            homePageEventSchedule.setClassDayIndex(homePageEvent.getClassDayIndex());
            homePageEventSchedule.setShowDateTime(homePageEvent.getShowDateTime());
            homePageEventSchedule.setTime(homePageEvent.getTime());
            for (HomePageEvents formatedHomePageEvent : formatedHomePageEvents) {
                if (formatedHomePageEvent.getId().longValue() == homePageEvent.getId().longValue()) {
                    List<HomePageEventSchedule> homePageEventSchedules = formatedHomePageEvent.getEventSchedule();
                    homePageEventSchedules.add(homePageEventSchedule);
                    formatedHomePageEvent.setEventSchedule(homePageEventSchedules);
                    eventExist = true;
                }
            }
            if (!eventExist) {
                List<HomePageEventSchedule> homePageEventSchedules = new ArrayList<>();
                homePageEventSchedules.add(homePageEventSchedule);
                homePageEvent.setEventSchedule(homePageEventSchedules);
                formatedHomePageEvents.add(homePageEvent);
            }
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("eventClasses", formatedHomePageEvents)
                .returnClientResponse();
    }

    @GetMapping("/home/{profileName}")
    public ResponseEntity getHomePageEventClasses(@PathVariable String profileName) {
        List<HomePageEvents> formatedHomePageEvents = new ArrayList<>();
        List<HomePageEvents> homePageEvents = eventClassService.getHomePageEventClasses(profileName);
        HashMap<Long, String> hash = eventClassService.getHomePageEventClassesProfiles();
        for (HomePageEvents homePageEvent : homePageEvents) {
            boolean eventExist = false;
            homePageEvent.setProfileName(hash.get(homePageEvent.getId()));
            HomePageEventSchedule homePageEventSchedule = new HomePageEventSchedule();
            homePageEventSchedule.setClassDayIndex(homePageEvent.getClassDayIndex());
            homePageEventSchedule.setShowDateTime(homePageEvent.getShowDateTime());
            homePageEventSchedule.setTime(homePageEvent.getTime());
            for (HomePageEvents formatedHomePageEvent : formatedHomePageEvents) {
                if (formatedHomePageEvent.getId().longValue() == homePageEvent.getId().longValue()) {
                    List<HomePageEventSchedule> homePageEventSchedules = formatedHomePageEvent.getEventSchedule();
                    homePageEventSchedules.add(homePageEventSchedule);
                    formatedHomePageEvent.setEventSchedule(homePageEventSchedules);
                    eventExist = true;
                }
            }
            if (!eventExist) {
                List<HomePageEventSchedule> homePageEventSchedules = new ArrayList<>();
                homePageEventSchedules.add(homePageEventSchedule);
                homePageEvent.setEventSchedule(homePageEventSchedules);
                formatedHomePageEvents.add(homePageEvent);
            }
        }

        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("eventClasses", formatedHomePageEvents)
                .returnClientResponse();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCountersByType(@PathVariable Long id) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("EventClass", eventClassService.getEventClass(id))
                .returnClientResponse();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getEventClassesByCategory(@PathVariable Long id) {
        List<EventClass> eventClasses = eventClassService.getEventClassesByCategory(id);
        for (EventClass eventClass : eventClasses) {
            Set<EventClassImage> eventClassImages = new HashSet<>();
            for (EventClassImage eventClassImage : eventClass.getEventClassImages()) {
                if (eventClassImage.getImageIndex() == 1) {
                    eventClassImages.add(eventClassImage);
                }
            }
            eventClass.setEventClassImages(eventClassImages);
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("EventClass", eventClasses)
                .returnClientResponse();
    }

    @GetMapping("/paging/category/{id}/{pageNumber}/{maxResult}")
    public ResponseEntity getEventClassesPaginationByCategory(@PathVariable Long id, @PathVariable Integer pageNumber, @PathVariable Integer maxResult) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("passes", eventClassService.getEventClassesPaginationByCategory(id, pageNumber, maxResult))
                .returnClientResponse();
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity getEventClassesByProfile(@PathVariable Long id) {
        List<EventClass> eventClasses = eventClassService.getEventClassesByProfile(id);
        for (EventClass eventClass : eventClasses) {
            Set<EventClassImage> eventClassImages = new HashSet<>();
            for (EventClassImage eventClassImage : eventClass.getEventClassImages()) {
                if (eventClassImage.getImageIndex() == 1) {
                    eventClassImages.add(eventClassImage);
                }
            }
            eventClass.setEventClassImages(eventClassImages);
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("EventClass", eventClasses)
                .returnClientResponse();
    }

    @GetMapping("/paging/profile/{id}/{pageNumber}/{maxResult}")
    public ResponseEntity getEventClassesPaginationByProfile(@PathVariable Long id, @PathVariable Integer pageNumber, @PathVariable Integer maxResult) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("passes", eventClassService.getEventClassesPaginationByProfile(id, pageNumber, maxResult))
                .returnClientResponse();
    }

    @GetMapping("/type/{id}")
    public ResponseEntity getEventClassesByType(@PathVariable Long id) {
        List<EventClass> eventClasses = eventClassService.getEventClassesByType(id);
        for (EventClass eventClass : eventClasses) {
            Set<EventClassImage> eventClassImages = new HashSet<>();
            for (EventClassImage eventClassImage : eventClass.getEventClassImages()) {
                if (eventClassImage.getImageIndex() == 1) {
                    eventClassImages.add(eventClassImage);
                }
            }
            eventClass.setEventClassImages(eventClassImages);
        }
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("EventClass", eventClasses)
                .returnClientResponse();
    }

    @GetMapping("/paging/type/{id}/{pageNumber}/{maxResult}")
    public ResponseEntity getEventClassesPaginationByType(@PathVariable Long id, @PathVariable Integer pageNumber, @PathVariable Integer maxResult) {
        return ResponseBuilder.getInstance()
                .setHttpStatus(HttpStatus.OK)
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("passes", eventClassService.getEventClassesPaginationByType(id, pageNumber, maxResult))
                .returnClientResponse();
    }

}
