package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.Favorite;
import java.util.List;

public interface FavoriteService {

    List<Favorite> getFavoritees();

    Favorite getFavorite(Long id);

    ResponseBodyEntity addFavorite(Long eventID);

    ResponseBodyEntity deleteFavorite(Long eventID);

    List<Favorite> getFavoritesByUserID(Long userID);

}
