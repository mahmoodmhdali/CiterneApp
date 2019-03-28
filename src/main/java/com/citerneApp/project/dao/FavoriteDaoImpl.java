package com.citerneApp.project.dao;

import com.citerneApp.project.model.Favorite;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("favoriteDao")
public class FavoriteDaoImpl extends AbstractDao<Long, Favorite> implements FavoriteDao {

    @Override
    public List<Favorite> getFavoritees() {
        Criteria criteria = createEntityCriteria();
        List<Favorite> favoritees = (List<Favorite>) criteria.list();
        return favoritees;
    }

    @Override
    public Favorite getFavorite(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        Favorite favorite = (Favorite) criteria.uniqueResult();
        return favorite;
    }

}
