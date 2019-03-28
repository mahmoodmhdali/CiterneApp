package com.citerneApp.project.dao;

import com.citerneApp.project.model.Favorite;
import java.util.List;

public interface FavoriteDao {

    List<Favorite> getFavoritees();

    Favorite getFavorite(Long id);

}
