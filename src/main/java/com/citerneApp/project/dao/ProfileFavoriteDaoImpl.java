package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.ProfileFavorite;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("profileFavoriteDao")
public class ProfileFavoriteDaoImpl extends AbstractDao<Long, ProfileFavorite> implements ProfileFavoriteDao {

    @Override
    public List<ProfileFavorite> getProfileFavoritees() {
        Criteria criteria = createEntityCriteria();
        List<ProfileFavorite> profileFavoritees = (List<ProfileFavorite>) criteria.list();
        return profileFavoritees;
    }

    @Override
    public ProfileFavorite getProfileFavorite(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        ProfileFavorite profileFavorite = (ProfileFavorite) criteria.uniqueResult();
        return profileFavorite;
    }

    @Override
    public ProfileFavorite addProfileFavorite(ProfileFavorite profileFavorite) {
        try {
            save(profileFavorite);
            return profileFavorite;
        } catch (Exception ex) {
            Logger.ERROR("1- Error ProfileFavoriteDaoImpl 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public void removeProfileFavorite(ProfileFavorite profileFavorite) {
        try {
            delete(profileFavorite);
        } catch (Exception ex) {
            Logger.ERROR("1- Error ProfileFavoriteDaoImpl 2 on API [" + ex.getMessage() + "]", "", "");
        }
    }

    @Override
    public List<ProfileFavorite> getProfileFavoritesByUserID(Long userID) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.createAlias("userProfile", "user");
            criteria.add(Restrictions.eq("user.id", userID));
            List<ProfileFavorite> profileFavorites = (List<ProfileFavorite>) criteria.list();
            for (ProfileFavorite profileFavorite : profileFavorites) {
                Hibernate.initialize(profileFavorite.getProfile());
            }
            return profileFavorites;
        } catch (Exception ex) {
            Logger.ERROR("1- Error ProfileFavoriteDaoImpl 3 on API [" + ex.getMessage() + "]", "userID= " + userID, "");
        }
        return null;
    }

    @Override
    public ProfileFavorite getProfileFavoriteByUserAndProfileID(Long userID, Long profileID) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.createAlias("userProfile", "user");
            criteria.add(Restrictions.eq("user.id", userID));
            criteria.createAlias("profile", "profile");
            criteria.add(Restrictions.eq("profile.id", profileID));
            ProfileFavorite profileFavorite = (ProfileFavorite) criteria.uniqueResult();
            return profileFavorite;
        } catch (Exception ex) {
            Logger.ERROR("1- Error ProfileFavoriteDaoImpl 4 on API [" + ex.getMessage() + "]", "userID= " + userID + " profileID= " + profileID, "");
        }
        return null;
    }

}
