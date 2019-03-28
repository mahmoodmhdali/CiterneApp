package com.citerneApp.project.service;

import com.citerneApp.project.model.Favorite;
import java.util.List;

public interface FavoriteService {

    List<Favorite> getFavoritees();

    Favorite getFavorite(Long id);

}
