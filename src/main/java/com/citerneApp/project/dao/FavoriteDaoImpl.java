package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
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
        try {
            save(favorite);
            return favorite;
        } catch (Exception ex) {
            Logger.ERROR("1- Error FavoriteDaoImpl 1 on API [" + ex.getMessage() + "]", "favorite= " + favorite, "");
        }
        return null;
    }

    @Override
    public void removeFavorite(Favorite favorite) {
        try {
            delete(favorite);
        } catch (Exception ex) {
            Logger.ERROR("1- Error FavoriteDaoImpl 2 on API [" + ex.getMessage() + "]", "favorite= " + favorite, "");
        }
    }

    @Override
    public List<Favorite> getFavoritesByUserID(Long userID) {
        try {
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
        } catch (Exception ex) {
            Logger.ERROR("1- Error FavoriteDaoImpl 3 on API [" + ex.getMessage() + "]", "userID= " + userID, "");
        }
        return null;
    }

    @Override
    public Favorite getFavoriteByUserAndEventID(Long userID, Long eventID) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.createAlias("userProfile", "user");
            criteria.add(Restrictions.eq("user.id", userID));
            criteria.createAlias("eventClass", "eventClass");
            criteria.add(Restrictions.eq("eventClass.id", eventID));
            Favorite favorite = (Favorite) criteria.uniqueResult();
            return favorite;
        } catch (Exception ex) {
            Logger.ERROR("1- Error FavoriteDaoImpl 4 on API [" + ex.getMessage() + "]", "userID= " + userID + " eventID= " + eventID, "");
        }
        return null;
    }

}
