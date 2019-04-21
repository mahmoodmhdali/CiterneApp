package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassDao;
import com.citerneApp.project.dao.FavoriteDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.Favorite;
import com.citerneApp.project.model.UserProfile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("favoriteService")
@Transactional
public class FavoriteServiceImpl extends AbstractService implements FavoriteService {
    
    @Autowired
    FavoriteDao favoriteDao;
    
    @Autowired
    EventClassDao eventClassDao;
    
    @Override
    public List<Favorite> getFavoritees() {
        return favoriteDao.getFavoritees();
    }
    
    @Override
    public Favorite getFavorite(Long id) {
        return favoriteDao.getFavorite(id);
    }
    
    @Override
    public List<Favorite> getFavoritesByUserID(Long userID) {
        return favoriteDao.getFavoritesByUserID(userID);
    }
    
    @Override
    public ResponseBodyEntity addFavorite(Long eventID) {
        UserProfile user = this.getAuthenticatedUser();
        if (user == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("user", "User not logged In")
                    .getResponse();
        }
        EventClass eventClass = eventClassDao.getEventClass(eventID);
        if (eventClass == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("event", "Event Does not exist")
                    .getResponse();
        }
        Favorite fav = favoriteDao.getFavoriteByUserAndEventID(user.getId(), eventID);
        if (fav != null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("event", "Event already added")
                    .getResponse();
        }
        Favorite favorite = new Favorite();
        favorite.setUserProfile(user);
        favorite.setEventClass(eventClass);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", favoriteDao.addFavorite(favorite))
                .getResponse();
    }
    
    @Override
    public ResponseBodyEntity deleteFavorite(Long eventID) {
        UserProfile user = this.getAuthenticatedUser();
        if (user == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("user", "User not logged In")
                    .getResponse();
        }
        Favorite favorite = favoriteDao.getFavoriteByUserAndEventID(user.getId(), eventID);
        if (favorite == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("favorite", "Favorite does not exist")
                    .getResponse();
        }
        favoriteDao.removeFavorite(favorite);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("favorite", "Successfully removed")
                .getResponse();
    }
}
