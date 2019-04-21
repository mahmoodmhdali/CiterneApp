package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.model.Favorite;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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

    @Override
    public Favorite addFavorite(Favorite favorite) {
        save(favorite);
        return favorite;
    }

    @Override
    public void removeFavorite(Favorite favorite) {
        delete(favorite);
    }

    @Override
    public List<Favorite> getFavoritesByUserID(Long userID) {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfile", "user");
        criteria.add(Restrictions.eq("user.id", userID));
        List<Favorite> favorites = (List<Favorite>) criteria.list();
        for (Favorite favorite : favorites) {
            Hibernate.initialize(favorite.getEventClass());
            Hibernate.initialize(favorite.getEventClass().getEventClassCastAndCredits());
            Hibernate.initialize(favorite.getEventClass().getEventClassImages());
            Hibernate.initialize(favorite.getEventClass().getEventClassSchedules());
            Hibernate.initialize(favorite.getEventClass().getProfileCollection());
        }
        return favorites;
    }

    @Override
    public Favorite getFavoriteByUserAndEventID(Long userID, Long eventID) {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfile", "user");
        criteria.add(Restrictions.eq("user.id", userID));
        criteria.createAlias("eventClass", "eventClass");
        criteria.add(Restrictions.eq("eventClass.id", eventID));
        Favorite favorite = (Favorite) criteria.uniqueResult();
        return favorite;
    }

}
