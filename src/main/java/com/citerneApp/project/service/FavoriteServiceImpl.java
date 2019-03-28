package com.citerneApp.project.service;

import com.citerneApp.project.dao.FavoriteDao;
import com.citerneApp.project.model.Favorite;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("favoriteService")
@Transactional
public class FavoriteServiceImpl extends AbstractService implements FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    @Override
    public List<Favorite> getFavoritees() {
        return favoriteDao.getFavoritees();
    }

    @Override
    public Favorite getFavorite(Long id) {
        return favoriteDao.getFavorite(id);
    }

}
