package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClass;
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
        save(profileFavorite);
        return profileFavorite;
    }

    @Override
    public void removeProfileFavorite(ProfileFavorite profileFavorite) {
        delete(profileFavorite);
    }

    @Override
    public List<ProfileFavorite> getProfileFavoritesByUserID(Long userID) {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfile", "user");
        criteria.add(Restrictions.eq("user.id", userID));
        List<ProfileFavorite> profileFavorites = (List<ProfileFavorite>) criteria.list();
        for (ProfileFavorite profileFavorite : profileFavorites) {
            Hibernate.initialize(profileFavorite.getProfile());
        }
        return profileFavorites;
    }

    @Override
    public ProfileFavorite getProfileFavoriteByUserAndProfileID(Long userID, Long profileID) {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("userProfile", "user");
        criteria.add(Restrictions.eq("user.id", userID));
        criteria.createAlias("profile", "profile");
        criteria.add(Restrictions.eq("profile.id", profileID));
        ProfileFavorite profileFavorite = (ProfileFavorite) criteria.uniqueResult();
        return profileFavorite;
    }

}
