package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassDao;
import com.citerneApp.project.dao.ProfileDao;
import com.citerneApp.project.dao.ProfileFavoriteDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.Profile;
import com.citerneApp.project.model.ProfileFavorite;
import com.citerneApp.project.model.UserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("profileFavoriteService")
@Transactional
public class ProfileFavoriteServiceImpl extends AbstractService implements ProfileFavoriteService {
    
    @Autowired
    ProfileFavoriteDao profileFavoriteDao;
    
    @Autowired
    ProfileDao profileDao;
    
    @Override
    public List<ProfileFavorite> getProfileFavoritees() {
        return profileFavoriteDao.getProfileFavoritees();
    }
    
    @Override
    public ProfileFavorite getProfileFavorite(Long id) {
        return profileFavoriteDao.getProfileFavorite(id);
    }
    
    @Override
    public List<ProfileFavorite> getProfileFavoritesByUserID(Long userID) {
        return profileFavoriteDao.getProfileFavoritesByUserID(userID);
    }
    
    @Override
    public ResponseBodyEntity addProfileFavorite(Long profileID) {
        UserProfile user = this.getAuthenticatedUser();
        if (user == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("user", "User not logged In")
                    .getResponse();
        }
        Profile profile = profileDao.getProfile(profileID);
        if (profile == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("profile", "Profile does not exist")
                    .getResponse();
        }
        ProfileFavorite fav = profileFavoriteDao.getProfileFavoriteByUserAndProfileID(user.getId(), profileID);
        if (fav != null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("profile", "Profile already added")
                    .getResponse();
        }
        ProfileFavorite profileFavorite = new ProfileFavorite();
        profileFavorite.setUserProfile(user);
        profileFavorite.setProfile(profile);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", profileFavoriteDao.addProfileFavorite(profileFavorite))
                .getResponse();
    }
    
    @Override
    public ResponseBodyEntity deleteProfileFavorite(Long profileID) {
        UserProfile user = this.getAuthenticatedUser();
        if (user == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("user", "User not logged In")
                    .getResponse();
        }
        ProfileFavorite profileFavorite = profileFavoriteDao.getProfileFavoriteByUserAndProfileID(user.getId(), profileID);
        if (profileFavorite == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("favorite", "Favorite does not exist")
                    .getResponse();
        }
        profileFavoriteDao.removeProfileFavorite(profileFavorite);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", "Successfully removed")
                .getResponse();
    }
}
