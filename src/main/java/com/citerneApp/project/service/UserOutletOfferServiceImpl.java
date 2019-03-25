package com.citerneApp.project.service;

import com.citerneApp.api.commons.ContextHolder;
import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.dao.OutletOfferTypeDao;
import com.citerneApp.project.dao.UserOutletInfoDao;
import com.citerneApp.project.dao.UserOutletOfferDao;
import com.citerneApp.project.dao.UserOutletOfferImagesDao;
import com.citerneApp.project.helpermodel.OffersPagination;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.UserOutletInfo;
import com.citerneApp.project.model.UserOutletOffer;
import com.citerneApp.project.model.UserOutletOfferImages;
import com.citerneApp.project.model.UserProfile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("userOutletOfferService")
@Transactional
public class UserOutletOfferServiceImpl extends AbstractService implements UserOutletOfferService {

    @Autowired
    UserOutletOfferDao userOutletOfferDao;

    @Autowired
    OutletOfferTypeDao outletOfferTypeDao;

    @Autowired
    UserOutletInfoDao userOutletInfoDao;

    @Autowired
    UserOutletOfferImagesDao userOutletOfferImagesDao;

    @Autowired
    ContextHolder context;

    @Override
    public List<UserOutletOffer> getUserOutletOffers() {
        return userOutletOfferDao.getUserOutletOffers();
    }

    @Override
    public UserOutletOffer getUserOutletOffer(Long id) {
        return userOutletOfferDao.getUserOutletOffer(id);
    }

    @Override
    public List<UserOutletOffer> getUserOutletOffersByType(Long type) {
        return userOutletOfferDao.getUserOutletOffersByType(type);
    }

    @Override
    public List<UserOutletOffer> getUserOutletOffersByOutletId(Long id) {
        return userOutletOfferDao.getUserOutletOffersByOutletId(id);
    }

    @Override
    public OffersPagination getOffersPagination(int pageNumber, int maxRes) {
        return userOutletOfferDao.getOffersPagination(pageNumber, maxRes);
    }

    @Override
    public ResponseBodyEntity addOffer(UserOutletOffer userOutletOffer, UserOutletInfo userOutletInfo,
            MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException {
//        if (userOutletOfferDao.getUserOutletOfferByName(userOutletOffer.getName()) != null) {
//            return ResponseBuilder.getInstance()
//                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
//                    .addHttpResponseEntityData("name", "Offer Name already taken")
//                    .getResponse();
//        }
        UserProfile loggedInUser = getAuthenticatedUser();
        if (userOutletInfo == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("userOutletInfo", "Outlet is required.")
                    .getResponse();
        }
        userOutletOffer.setUserOutletInfo(userOutletInfoDao.getUserOutletInfo(userOutletInfo.getId()));
        if (userOutletOffer.getUserOutletInfo() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("userOutletInfo", "Outlet does not exist.")
                    .getResponse();
        }

        if (userOutletOffer.getOutletOfferType() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("outletOfferType", "Type is required.")
                    .getResponse();
        }
        userOutletOffer.setOutletOfferType(outletOfferTypeDao.getOutletOfferType(userOutletOffer.getOutletOfferType().getId()));
        if (userOutletOffer.getOutletOfferType() == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("outletOfferType", "Type does not exist.")
                    .getResponse();
        }
        Collection<UserOutletOfferImages> userOutletOfferImages = new ArrayList<>();
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
        String locationfile = context.getCatalina().getCatalinaWorkInstanceDir() + "/OffersImages";
        Path dir = Paths.get(locationfile);
        int numberOfNotAlLowed = 0;
        if (image1 != null) {
            String imageExtension = FilenameUtils.getExtension(image1.getOriginalFilename());
            if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                try {
                    String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-1." + imageExtension;
                    Path originalFile = dir.resolve(fileName);
                    Files.copy(image1.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                    UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                    userOutletOfferImage.setFileName(image1.getOriginalFilename().replace("." + FilenameUtils.getExtension(image1.getOriginalFilename()), ""));
                    userOutletOfferImage.setImageIndex(1);
                    userOutletOfferImage.setPath("/OffersImages/" + fileName);
                    userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                    userOutletOfferImages.add(userOutletOfferImage);
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
                    UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                    userOutletOfferImage.setFileName(image2.getOriginalFilename().replace("." + FilenameUtils.getExtension(image2.getOriginalFilename()), ""));
                    userOutletOfferImage.setImageIndex(2);
                    userOutletOfferImage.setPath("/OffersImages/" + fileName);
                    userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                    userOutletOfferImages.add(userOutletOfferImage);
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
                    UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                    userOutletOfferImage.setFileName(image3.getOriginalFilename().replace("." + FilenameUtils.getExtension(image3.getOriginalFilename()), ""));
                    userOutletOfferImage.setImageIndex(3);
                    userOutletOfferImage.setPath("/OffersImages/" + fileName);
                    userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                    userOutletOfferImages.add(userOutletOfferImage);
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
                    UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                    userOutletOfferImage.setFileName(image4.getOriginalFilename().replace("." + FilenameUtils.getExtension(image4.getOriginalFilename()), ""));
                    userOutletOfferImage.setImageIndex(4);
                    userOutletOfferImage.setPath("/OffersImages/" + fileName);
                    userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                    userOutletOfferImages.add(userOutletOfferImage);
                } catch (Exception ex) {
                    Logger.ERROR("1- Error addOffer 4 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                numberOfNotAlLowed++;
            }
        }
        userOutletOffer.setUserOutletOfferImagesCollection(userOutletOfferImages);
        userOutletOfferDao.addUser(userOutletOffer);
        if (numberOfNotAlLowed > 0) {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("offer", userOutletOffer)
                    .addHttpResponseEntityData("validation", numberOfNotAlLowed + " not uploaded images. Reason: extension not valid.")
                    .getResponse();
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("offer", userOutletOffer)
                    .getResponse();
        }
    }

    @Override
    public ResponseBodyEntity editOffer(UserOutletOffer userOutletOffer, UserOutletInfo userOutletInfo,
            MultipartFile image1, MultipartFile image2, MultipartFile image3, MultipartFile image4) throws IOException {
        UserOutletOffer persistantUserOutletOffer = userOutletOfferDao.getUserOutletOffer(userOutletOffer.getId());
        if (persistantUserOutletOffer != null) {
            UserProfile loggedInUser = getAuthenticatedUser();
            if (userOutletInfo == null) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("userOutletInfo", "Outlet is required.")
                        .getResponse();
            }
            persistantUserOutletOffer.setUserOutletInfo(userOutletInfoDao.getUserOutletInfo(userOutletInfo.getId()));
            if (persistantUserOutletOffer.getUserOutletInfo() == null) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("userOutletInfo", "Outlet does not exist.")
                        .getResponse();
            }

            if (userOutletOffer.getOutletOfferType() == null) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("outletOfferType", "Type is required.")
                        .getResponse();
            }
            persistantUserOutletOffer.setOutletOfferType(outletOfferTypeDao.getOutletOfferType(userOutletOffer.getOutletOfferType().getId()));
            if (persistantUserOutletOffer.getOutletOfferType() == null) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("outletOfferType", "Type does not exist.")
                        .getResponse();
            }
            persistantUserOutletOffer.setName(userOutletOffer.getName());
            persistantUserOutletOffer.setNumberOfUsage(userOutletOffer.getNumberOfUsage());
            persistantUserOutletOffer.setTypeOfUsage(userOutletOffer.getTypeOfUsage());
            persistantUserOutletOffer.setInfo(userOutletOffer.getInfo());
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
            String locationfile = context.getCatalina().getCatalinaWorkInstanceDir() + "/OffersImages";
            Path dir = Paths.get(locationfile);
            int numberOfNotAlLowed = 0;
            Collection<UserOutletOfferImages> userOutletOfferImages = new ArrayList<>();
            Collection<UserOutletOfferImages> persistantUserOutletOfferImages = persistantUserOutletOffer.getUserOutletOfferImagesCollection();
            Collection<UserOutletOfferImages> toKeepImages = new ArrayList<>();
            if (image1 != null) {
                String imageExtension = FilenameUtils.getExtension(image1.getOriginalFilename());
                if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                    try {
                        String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-1." + imageExtension;
                        Path originalFile = dir.resolve(fileName);
                        Files.copy(image1.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                        UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                        userOutletOfferImage.setFileName(image1.getOriginalFilename().replace("." + FilenameUtils.getExtension(image1.getOriginalFilename()), ""));
                        userOutletOfferImage.setImageIndex(1);
                        userOutletOfferImage.setPath("/OffersImages/" + fileName);
                        userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                        userOutletOfferImages.add(userOutletOfferImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 1 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image1 == null && !userOutletOffer.getImageName1().equals("")) {
                for (UserOutletOfferImages image : persistantUserOutletOfferImages) {
                    if (image.getImageIndex() == 1) {
                        userOutletOfferImages.add(image);
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
                        UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                        userOutletOfferImage.setFileName(image2.getOriginalFilename().replace("." + FilenameUtils.getExtension(image2.getOriginalFilename()), ""));
                        userOutletOfferImage.setImageIndex(2);
                        userOutletOfferImage.setPath("/OffersImages/" + fileName);
                        userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                        userOutletOfferImages.add(userOutletOfferImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 2 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image2 == null && !userOutletOffer.getImageName2().equals("")) {
                for (UserOutletOfferImages image : persistantUserOutletOfferImages) {
                    if (image.getImageIndex() == 2) {
                        userOutletOfferImages.add(image);
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
                        UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                        userOutletOfferImage.setFileName(image3.getOriginalFilename().replace("." + FilenameUtils.getExtension(image3.getOriginalFilename()), ""));
                        userOutletOfferImage.setImageIndex(3);
                        userOutletOfferImage.setPath("/OffersImages/" + fileName);
                        userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                        userOutletOfferImages.add(userOutletOfferImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 3 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image3 == null && !userOutletOffer.getImageName3().equals("")) {
                for (UserOutletOfferImages image : persistantUserOutletOfferImages) {
                    if (image.getImageIndex() == 3) {
                        userOutletOfferImages.add(image);
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
                        UserOutletOfferImages userOutletOfferImage = new UserOutletOfferImages();
                        userOutletOfferImage.setFileName(image4.getOriginalFilename().replace("." + FilenameUtils.getExtension(image4.getOriginalFilename()), ""));
                        userOutletOfferImage.setImageIndex(4);
                        userOutletOfferImage.setPath("/OffersImages/" + fileName);
                        userOutletOfferImage.setUserOutletOffer(userOutletOffer);
                        userOutletOfferImages.add(userOutletOfferImage);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 4 on API [" + ex.getMessage() + "]", "", "");
                    }
                } else {
                    numberOfNotAlLowed++;
                }
            } else if (image4 == null && !userOutletOffer.getImageName4().equals("")) {
                for (UserOutletOfferImages image : persistantUserOutletOfferImages) {
                    if (image.getImageIndex() == 4) {
                        userOutletOfferImages.add(image);
                        toKeepImages.add(image);
                    }
                }
            }

            for (UserOutletOfferImages image : persistantUserOutletOfferImages) {
                if (!toKeepImages.contains(image)) {
                    Path originalFile = dir.resolve(image.getPath().replace("/OffersImages/", ""));
                    try {
                        Files.delete(originalFile);
                        userOutletOfferImagesDao.deleteImage(image);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editOffer 5 on API [" + ex.getMessage() + "]", image, "");
                    }
                }
            }

            persistantUserOutletOffer.setUserOutletOfferImagesCollection(userOutletOfferImages);
            if (numberOfNotAlLowed > 0) {
                return ResponseBuilder.getInstance().
                        setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                        .addHttpResponseEntityData("offer", persistantUserOutletOffer)
                        .addHttpResponseEntityData("validation", numberOfNotAlLowed + " not uploaded images. Reason: extension not valid.")
                        .getResponse();
            } else {
                return ResponseBuilder.getInstance().
                        setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                        .addHttpResponseEntityData("offer", persistantUserOutletOffer)
                        .getResponse();
            }
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SOURCE_NOT_FOUND)
                    .addHttpResponseEntityData("Message", "Offer not found")
                    .getResponse();
        }
    }

}
