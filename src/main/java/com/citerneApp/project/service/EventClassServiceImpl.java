package com.citerneApp.project.service;

import com.citerneApp.api.commons.ContextHolder;
import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.dao.EventClassCastAndCreditDao;
import com.citerneApp.project.dao.EventClassCountryDao;
import com.citerneApp.project.dao.EventClassDao;
import com.citerneApp.project.dao.EventClassImageDao;
import com.citerneApp.project.dao.EventClassMediaDao;
import com.citerneApp.project.dao.EventClassScheduleDao;
import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.EventClassCastAndCredit;
import com.citerneApp.project.model.EventClassImage;
import com.citerneApp.project.model.EventClassMedia;
import com.citerneApp.project.model.EventClassSchedule;
import com.citerneApp.project.model.UserProfile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("eventClassService")
@Transactional
public class EventClassServiceImpl extends AbstractService implements EventClassService {

    @Autowired
    EventClassDao eventClassDao;

    @Autowired
    EventClassImageDao eventClassImageDao;

    @Autowired
    EventClassCountryDao eventClassCountryDao;

    @Autowired
    EventClassMediaDao eventClassMediaDao;

    @Autowired
    EventClassScheduleDao eventClassScheduleDao;

    @Autowired
    EventClassCastAndCreditDao eventClassCastAndCreditDao;

    @Autowired
    ContextHolder context;

    @Override
    public List<HomePageEvents> getHomePageEventClasses() {
        return eventClassDao.getHomePageEventClasses();
    }

    @Override
    public HashMap<Long, String> getHomePageEventClassesProfiles() {
        return eventClassDao.getHomePageEventClassesProfiles();
    }

    @Override
    public List<HomePageEvents> getHomePageEventClasses(String profileName) {
        return eventClassDao.getHomePageEventClasses(profileName);
    }

    @Override
    public List<EventClass> getEventClasses() {
        return eventClassDao.getEventClasses();
    }

    @Override
    public void getEventClassesForMidnightCheck() {
        List<EventClass> eventClasses = eventClassDao.getEventClassesForMidnightCheck();
        for (EventClass eventClasse : eventClasses) {
            boolean toRemove = true;
            for (EventClassSchedule eventClassSchedule : eventClasse.getEventClassSchedules()) {
                if (eventClassSchedule.getShowDateTime().compareTo(new Date()) > 0) {
                    toRemove = false;
                }
            }
            if (toRemove) {
                eventClasse.setDeletedDate(new Date());
            }
        }
    }

    @Override
    public EventClassPagination getEventClassesPagination(int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPagination(pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByCategory(Long categoryID) {
        return eventClassDao.getEventClassesByCategory(categoryID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByCategory(Long categoryID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByCategory(categoryID, pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByProfile(Long profileID) {
        return eventClassDao.getEventClassesByProfile(profileID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByProfile(Long profileID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByProfile(profileID, pageNumber, maxRes);
    }

    @Override
    public List<EventClass> getEventClassesByType(Long typeID) {
        return eventClassDao.getEventClassesByType(typeID);
    }

    @Override
    public EventClassPagination getEventClassesPaginationByType(Long typeID, int pageNumber, int maxRes) {
        return eventClassDao.getEventClassesPaginationByType(typeID, pageNumber, maxRes);
    }

    @Override
    public EventClass getEventClass(Long id) {
        return eventClassDao.getEventClass(id);
    }

    @Override
    public ResponseBodyEntity addEventClass(EventClass eventClass) {
        if (eventClass.getEventClassCategory() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassCategory", "Category is required.")
                    .getResponse();
        }

        if (eventClass.getEventClassType() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassType", "Type is required.")
                    .getResponse();
        }

        if (eventClass.getProfileCollection() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("profileCollection", "At least one profile should be selected.")
                    .getResponse();
        }

        if (eventClass.getEventClassSchedules() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassSchedules", "At least one schedule should be added.")
                    .getResponse();
        }
        eventClass.setEventClassCountry(eventClassCountryDao.getEventClassCountry(1L));
        eventClassDao.addEventClass(eventClass);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("eventClass", eventClass)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity editEventClass(EventClass eventClass) {
        if (eventClass.getEventClassCategory() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassCategory", "Category is required.")
                    .getResponse();
        }

        if (eventClass.getProfileCollection() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("profileCollection", "At least one profile should be selected.")
                    .getResponse();
        }

        if (eventClass.getEventClassSchedules() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassSchedules", "At least one schedule should be added.")
                    .getResponse();
        }
        EventClass persistantEventClass = eventClassDao.getEventClass(eventClass.getId());
        if (persistantEventClass != null) {
            if (persistantEventClass.getEventClassType().getId() == 1L) {
                eventClassMediaDao.deleteEventClassMedia(eventClass.getId());
                eventClassCastAndCreditDao.deleteEventClassCastAndCredit(eventClass.getId());
            }
            eventClassScheduleDao.deleteEventClassSchedule(eventClass.getId());
            if (eventClass.getEventClassSchedules() != null) {
                for (EventClassSchedule eventClassSchedule : eventClass.getEventClassSchedules()) {
                    eventClassSchedule.setEventClass(persistantEventClass);
                }
            }
            if (eventClass.getEventClassCastAndCredits() != null) {
                for (EventClassCastAndCredit eventClassCastAndCredit : eventClass.getEventClassCastAndCredits()) {
                    eventClassCastAndCredit.setEventClass(persistantEventClass);
                }
            }
            if (eventClass.getEventClassMedias() != null) {
                for (EventClassMedia eventClassMedia : eventClass.getEventClassMedias()) {
                    eventClassMedia.setEventClass(persistantEventClass);
                }
            }
            persistantEventClass.setTitle(eventClass.getTitle());
            persistantEventClass.setProfileCollection(eventClass.getProfileCollection());
            persistantEventClass.setEventClassCategory(eventClass.getEventClassCategory());
            persistantEventClass.setTicketingURL(eventClass.getTicketingURL());
            persistantEventClass.setDuration(eventClass.getDuration());
            persistantEventClass.setAbout(eventClass.getAbout());
            persistantEventClass.setEventClassCastAndCredits(eventClass.getEventClassCastAndCredits());
            persistantEventClass.setEventClassMedias(eventClass.getEventClassMedias());
            persistantEventClass.setEventClassSchedules(eventClass.getEventClassSchedules());
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("EventClass", "Success editing event/class")
                    .getResponse();
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SOURCE_NOT_FOUND)
                    .addHttpResponseEntityData("Message", "Event/Class not found")
                    .getResponse();
        }
    }

    @Override
    public ResponseBodyEntity deleteEventClass(Long eventClassID) {
        EventClass persistantEventClass = eventClassDao.getEventClass(eventClassID);
        if (persistantEventClass != null) {
            persistantEventClass.setDeletedDate(new Date());
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("EventClass", "Success removing event/class")
                    .getResponse();
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SOURCE_NOT_FOUND)
                    .addHttpResponseEntityData("Message", "Event/Class not found")
                    .getResponse();
        }
    }

    @Override
    public ResponseBodyEntity editEventClassImages(EventClass eventClass,
            MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException {
        EventClass persistantEventClass = eventClassDao.getEventClass(eventClass.getId());
        if (persistantEventClass != null) {
            UserProfile loggedInUser = getAuthenticatedUser();
            if (image1 != null) {
                String extension = FilenameUtils.getExtension(image1.getOriginalFilename()).toLowerCase();
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    return ResponseBuilder.getInstance()
                            .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                            .addHttpResponseEntityData("imageName1", "Please upload .jpg .jpeg or png images only.")
                            .getResponse();
                }
            }
            if (image2 != null) {
                String extension = FilenameUtils.getExtension(image2.getOriginalFilename()).toLowerCase();
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    return ResponseBuilder.getInstance()
                            .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                            .addHttpResponseEntityData("imageName2", "Please upload .jpg .jpeg or png images only.")
                            .getResponse();
                }
            }
            if (image3 != null) {
                String extension = FilenameUtils.getExtension(image3.getOriginalFilename()).toLowerCase();
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    return ResponseBuilder.getInstance()
                            .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                            .addHttpResponseEntityData("imageName3", "Please upload .jpg .jpeg or png images only.")
                            .getResponse();
                }
            }
            if (image4 != null) {
                String extension = FilenameUtils.getExtension(image4.getOriginalFilename()).toLowerCase();
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    return ResponseBuilder.getInstance()
                            .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                            .addHttpResponseEntityData("imageName4", "Please upload .jpg .jpeg or png images only.")
                            .getResponse();
                }
            }
            String locationfile = context.getCatalina().getCatalinaWorkInstanceDir() + "/EventClasseImages";
            Path dir = Paths.get(locationfile);
            int numberOfNotAlLowed = 0;
            Collection<EventClassImage> eventClassImages = new ArrayList<>();
            Collection<EventClassImage> persistantEventClassImages = persistantEventClass.getEventClassImages();
            Collection<EventClassImage> toKeepImages = new ArrayList<>();
            if (image1 != null) {
                String imageExtension = FilenameUtils.getExtension(image1.getOriginalFilename());
                if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                    try {
                        String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-1." + imageExtension;
                        Path originalFile = dir.resolve(fileName);
                        Files.copy(image1.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                        EventClassImage eventClassImage = new EventClassImage();
                        eventClassImage.setFileName(image1.getOriginalFilename().replace("." + FilenameUtils.getExtension(image1.getOriginalFilename()), ""));
                        eventClassImage.setImageIndex(1);
                        eventClassImage.setPath("/EventClasseImages/" + fileName);
                        eventClassImage.setEventClass(persistantEventClass);
                        eventClassImages.add(eventClassImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error EventClassServiceImpl 1 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image1 == null && !eventClass.getImageName1().equals("")) {
                for (EventClassImage image : persistantEventClassImages) {
                    if (image.getImageIndex() == 1) {
                        eventClassImages.add(image);
                        toKeepImages.add(image);
                    }
                }
            }

            if (image2 != null) {
                String imageExtension = FilenameUtils.getExtension(image2.getOriginalFilename());
                if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                    try {
                        String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-2." + imageExtension;
                        Path originalFile = dir.resolve(fileName);
                        Files.copy(image2.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                        EventClassImage eventClassImage = new EventClassImage();
                        eventClassImage.setFileName(image2.getOriginalFilename().replace("." + FilenameUtils.getExtension(image2.getOriginalFilename()), ""));
                        eventClassImage.setImageIndex(2);
                        eventClassImage.setPath("/EventClasseImages/" + fileName);
                        eventClassImage.setEventClass(persistantEventClass);
                        eventClassImages.add(eventClassImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error EventClassServiceImpl 2 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image2 == null && !eventClass.getImageName2().equals("")) {
                for (EventClassImage image : persistantEventClassImages) {
                    if (image.getImageIndex() == 2) {
                        eventClassImages.add(image);
                        toKeepImages.add(image);
                    }
                }
            }

            if (image3 != null) {
                String imageExtension = FilenameUtils.getExtension(image3.getOriginalFilename());
                if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                    try {
                        String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-3." + imageExtension;
                        Path originalFile = dir.resolve(fileName);
                        Files.copy(image3.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                        EventClassImage eventClassImage = new EventClassImage();
                        eventClassImage.setFileName(image3.getOriginalFilename().replace("." + FilenameUtils.getExtension(image3.getOriginalFilename()), ""));
                        eventClassImage.setImageIndex(3);
                        eventClassImage.setPath("/EventClasseImages/" + fileName);
                        eventClassImage.setEventClass(persistantEventClass);
                        eventClassImages.add(eventClassImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error EventClassServiceImpl 3 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image3 == null && !eventClass.getImageName3().equals("")) {
                for (EventClassImage image : persistantEventClassImages) {
                    if (image.getImageIndex() == 3) {
                        eventClassImages.add(image);
                        toKeepImages.add(image);
                    }
                }
            }
            if (image4 != null) {
                String imageExtension = FilenameUtils.getExtension(image4.getOriginalFilename());
                if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                    try {
                        String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-4." + imageExtension;
                        Path originalFile = dir.resolve(fileName);
                        Files.copy(image4.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                        EventClassImage eventClassImage = new EventClassImage();
                        eventClassImage.setFileName(image4.getOriginalFilename().replace("." + FilenameUtils.getExtension(image4.getOriginalFilename()), ""));
                        eventClassImage.setImageIndex(4);
                        eventClassImage.setPath("/EventClasseImages/" + fileName);
                        eventClassImage.setEventClass(persistantEventClass);
                        eventClassImages.add(eventClassImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error EventClassServiceImpl 4 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image4 == null && !eventClass.getImageName4().equals("")) {
                for (EventClassImage image : persistantEventClassImages) {
                    if (image.getImageIndex() == 4) {
                        eventClassImages.add(image);
                        toKeepImages.add(image);
                    }
                }
            }

            for (EventClassImage image : persistantEventClassImages) {
                if (!toKeepImages.contains(image)) {
                    Path originalFile = dir.resolve(image.getPath().replace("/EventClasseImages/", ""));
                    try {
                        Files.delete(originalFile);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error EventClassServiceImpl 5 on API [" + ex.getMessage() + "]", image, "");
                    }
                }
            }
            eventClassDao.deleteEventClassImages(persistantEventClass.getId());
            for (EventClassImage eventClassImage : eventClassImages) {
                eventClassImageDao.addEventClassImage(eventClassImage);
            }
            if (numberOfNotAlLowed > 0) {
                return ResponseBuilder.getInstance().
                        setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                        .addHttpResponseEntityData("eventClass", persistantEventClass)
                        .addHttpResponseEntityData("validation", numberOfNotAlLowed + " not uploaded images. Reason: extension not valid.")
                        .getResponse();
            } else {
                return ResponseBuilder.getInstance().
                        setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                        .addHttpResponseEntityData("eventClass", persistantEventClass)
                        .getResponse();
            }
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SOURCE_NOT_FOUND)
                    .addHttpResponseEntityData("Message", "Event/Class not found")
                    .getResponse();
        }
    }

}
