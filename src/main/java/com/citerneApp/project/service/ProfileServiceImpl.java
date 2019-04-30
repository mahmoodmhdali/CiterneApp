package com.citerneApp.project.service;

import com.citerneApp.api.commons.ContextHolder;
import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.dao.ProfileDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.Profile;
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

@Service("profileService")
@Transactional
public class ProfileServiceImpl extends AbstractService implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Autowired
    ContextHolder context;

    @Override
    public List<Profile> getProfilees() {
        return profileDao.getProfilees();
    }

    @Override
    public Profile getProfile(Long id) {
        return profileDao.getProfile(id);
    }

    @Override
    public Profile getProfile(String name) {
        return profileDao.getProfile(name);
    }

    @Override
    public ResponseBodyEntity addProfile(Profile profile, MultipartFile image1) throws IOException {
        Profile persistantProfile = profileDao.getProfile(profile.getName());
        if (persistantProfile != null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("name", "Profile Name already exist.")
                    .getResponse();
        }
        UserProfile loggedInUser = getAuthenticatedUser();
        if (image1 != null) {
            String extension = FilenameUtils.getExtension(image1.getOriginalFilename()).toLowerCase();
            if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("imageName1", "Please upload .jpg .jpeg or png images only.")
                        .getResponse();
            }
        } else {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("imageName1", "Image is required.")
                    .getResponse();
        }
        String locationfile = context.getCatalina().getCatalinaWorkInstanceDir() + "/ProfilesImages";
        Path dir = Paths.get(locationfile);
        if (image1 != null) {
            String imageExtension = FilenameUtils.getExtension(image1.getOriginalFilename());
            if (imageExtension.toLowerCase().equals("jpg") || imageExtension.toLowerCase().equals("jpeg") || imageExtension.toLowerCase().equals("png")) {
                try {
                    String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-1." + imageExtension;
                    Path originalFile = dir.resolve(fileName);
                    Files.copy(image1.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                    profile.setFileName(image1.getOriginalFilename().replace("." + FilenameUtils.getExtension(image1.getOriginalFilename()), ""));
                    profile.setImagePath("/ProfilesImages/" + fileName);
                } catch (Exception ex) {
                    Logger.ERROR("1- Error addPass 1 on API [" + ex.getMessage() + "]", "", "");
                }
            } else {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("imageName1", "Please upload .jpg .jpeg or png images only.")
                        .getResponse();
            }
        }
        profileDao.addProfile(profile);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("package", "Success adding package")
                .getResponse();
    }

    @Override
    public ResponseBodyEntity editProfile(Profile profile, MultipartFile image1) throws IOException {
        Profile persistantProfile = profileDao.getProfile(profile.getId());
        if (persistantProfile != null) {
            UserProfile loggedInUser = getAuthenticatedUser();
            persistantProfile.setAbout(profile.getAbout());
            if (image1 != null) {
                String extension = FilenameUtils.getExtension(image1.getOriginalFilename()).toLowerCase();
                if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                    return ResponseBuilder.getInstance()
                            .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                            .addHttpResponseEntityData("imageName1", "Please upload .jpg .jpeg or png images only.")
                            .getResponse();
                }
            }
            String locationfile = context.getCatalina().getCatalinaWorkInstanceDir() + "/ProfilesImages";
            Path dir = Paths.get(locationfile);
            if (image1 != null) {
                String toRemoveImage = persistantProfile.getImagePath();
                String imageExtension = FilenameUtils.getExtension(image1.getOriginalFilename());
                String fileName = loggedInUser.getId() + "-" + System.currentTimeMillis() + "-1." + imageExtension;
                Path originalFile = dir.resolve(fileName);
                Files.copy(image1.getInputStream(), originalFile, StandardCopyOption.REPLACE_EXISTING);
                persistantProfile.setFileName(image1.getOriginalFilename().replace("." + FilenameUtils.getExtension(image1.getOriginalFilename()), ""));
                persistantProfile.setImagePath("/ProfilesImages/" + fileName);
                if (toRemoveImage != null) {
                    Path oldFile = dir.resolve(toRemoveImage.replace("/ProfilesImages/", ""));
                    Files.delete(oldFile);
                }
            } else if (image1 == null && profile.getImageName1().equals("")) {
                String toRemoveImage = persistantProfile.getImagePath();
                if (toRemoveImage != null) {
                    Path oldFile = dir.resolve(toRemoveImage.replace("/ProfilesImages/", ""));
                    try {
                        Files.delete(oldFile);
                    } catch (Exception ex) {
                        Logger.ERROR("1- Error editPass 1 on API [" + ex.getMessage() + "]", oldFile, "");
                    }
                }
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("imageName1", "Image is required.")
                        .getResponse();
            }
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                    .addHttpResponseEntityData("Profile", "Success editing profile")
                    .getResponse();
        } else {
            return ResponseBuilder.getInstance().
                    setHttpResponseEntityResultCode(ResponseCode.SOURCE_NOT_FOUND)
                    .addHttpResponseEntityData("Message", "Profile not found")
                    .getResponse();
        }
    }

}
