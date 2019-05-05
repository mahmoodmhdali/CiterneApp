package com.citerneApp.project.service;

import com.citerneApp.api.commons.ContextHolder;
import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.dao.EventClassCountryDao;
import com.citerneApp.project.dao.EventClassDao;
import com.citerneApp.project.helpermodel.EventClassPagination;
import com.citerneApp.project.helpermodel.HomePageEvents;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.EventClassImage;
import com.citerneApp.project.model.UserProfile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
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
    EventClassCountryDao eventClassCountryDao;

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

        if (eventClass.getEventClassCastAndCredits() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassCastAndCredits", "At least one cast and credit should be added.")
                    .getResponse();
        }

        if (eventClass.getEventClassMedias() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClassMedias", "At least one media should be added.")
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
                .addHttpResponseEntityData("eventClass", "Success adding event/class")
                .getResponse();
    }

    @Override
    public ResponseBodyEntity editEventClass(EventClass eventClass) {
        EventClass persistantEventClass = eventClassDao.getEventClass(eventClass.getId());
        if (persistantEventClass != null) {
            persistantEventClass.setTitle(eventClass.getTitle());
            persistantEventClass.setEventClassCategory(eventClass.getEventClassCategory());
            persistantEventClass.setDuration(eventClass.getDuration());
            persistantEventClass.setTicketingURL(eventClass.getTicketingURL());
            persistantEventClass.setAbout(eventClass.getAbout());
            persistantEventClass.setEventClassType(eventClass.getEventClassType());
            persistantEventClass.setProfileCollection(eventClass.getProfileCollection());
            persistantEventClass.setEventClassCastAndCredits(eventClass.getEventClassCastAndCredits());
            persistantEventClass.setEventClassMedias(eventClass.getEventClassMedias());
            persistantEventClass.setEventClassSchedules(eventClass.getEventClassSchedules());
//            profileMediaDao.deleteProfileMediasForProfile(persistantProfile.getId());
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
    public ResponseBodyEntity addEventClassImages(EventClass eventClass,
            MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException {
        UserProfile loggedInUser = getAuthenticatedUser();
        if (eventClass == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClass", "Event/Class does not exist.")
                    .getResponse();
        }
        EventClass persistantEventClass = eventClassDao.getEventClass(eventClass.getId());
        if (persistantEventClass == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("eventClass", "Event/Class does not exist.")
                    .getResponse();
        }
        Collection<EventClassImage> eventClassImages = new ArrayList<>();
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
                    Logger.ERROR("1- Error addOffer 1 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                numberOfNotAlLowed++;
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
                    Logger.ERROR("1- Error addOffer 2 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                numberOfNotAlLowed++;
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
                    Logger.ERROR("1- Error addOffer 3 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                numberOfNotAlLowed++;
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
                    Logger.ERROR("1- Error addOffer 4 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                numberOfNotAlLowed++;
            }
        }
        persistantEventClass.setEventClassImages(eventClassImages);
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
                        Logger.ERROR("1- Error editOffer 1 on API [" + ex.getMessage() + "]", "", "");
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
                        Logger.ERROR("1- Error editOffer 2 on API [" + ex.getMessage() + "]", "", "");
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
                        Logger.ERROR("1- Error editOffer 3 on API [" + ex.getMessage() + "]", "", "");
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
                        Logger.ERROR("1- Error editOffer 4 on API [" + ex.getMessage() + "]", "", "");
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
//                        userOutletOfferImagesDao.deleteImage(image);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 5 on API [" + ex.getMessage() + "]", image, "");
                    }
                }
            }

            persistantEventClass.setEventClassImages(eventClassImages);
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
