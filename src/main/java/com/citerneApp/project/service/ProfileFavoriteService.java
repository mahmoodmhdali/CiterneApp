package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.ProfileFavorite;
import java.util.List;

public interface ProfileFavoriteService {

    List<ProfileFavorite> getProfileFavoritees();

    ProfileFavorite getProfileFavorite(Long id);

    ResponseBodyEntity addProfileFavorite(Long profileID);

    ResponseBodyEntity deleteProfileFavorite(Long profileID);

    List<ProfileFavorite> getProfileFavoritesByUserID(Long userID);

}
