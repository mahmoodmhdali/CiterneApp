package com.citerneApp.project.dao;

import com.citerneApp.project.model.ProfileFavorite;
import java.util.List;

public interface ProfileFavoriteDao {

    List<ProfileFavorite> getProfileFavoritees();

    ProfileFavorite getProfileFavorite(Long id);

    ProfileFavorite addProfileFavorite(ProfileFavorite profileFavorite);

    void removeProfileFavorite(ProfileFavorite profileFavorite);

    List<ProfileFavorite> getProfileFavoritesByUserID(Long userID);

    ProfileFavorite getProfileFavoriteByUserAndProfileID(Long userID, Long profileID);

}
