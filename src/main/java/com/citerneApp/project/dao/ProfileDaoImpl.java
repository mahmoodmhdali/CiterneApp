package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.helpermodel.ProfilesPagination;
import com.citerneApp.project.model.Profile;
import com.citerneApp.project.model.ProfileMedia;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("profileDao")
public class ProfileDaoImpl extends AbstractDao<Long, Profile> implements ProfileDao {

    @Override
    public List<Profile> getProfilees() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("name"));
        criteria.add(Restrictions.isNull("deletedDate"));
        List<Profile> profilees = (List<Profile>) criteria.list();
        return profilees;
    }

    @Override
    public ProfilesPagination getProfilesPagination(int pageNumber, int maxRes) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.addOrder(Order.asc("name"));
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            criteria.setProjection(Projections.rowCount());
            Number totalResults = (Number) criteria.uniqueResult();
            criteria.setProjection(null);
            criteria.setResultTransformer(Criteria.ROOT_ENTITY);
            criteria.setFirstResult((pageNumber - 1) * maxRes);
            criteria.setMaxResults(maxRes);
            List<Profile> profiles = (List<Profile>) criteria.list();
            for (Profile profile : profiles) {
                Hibernate.initialize(profile.getProfileMedias());
            }
            int currentPage = pageNumber;
            int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
            ProfilesPagination adminPassesPagination = new ProfilesPagination(maxPages, currentPage, totalResults.intValue(), profiles);
            return adminPassesPagination;
        } catch (Exception ex) {
            Logger.ERROR("1- Error AdminPassesDao 2 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public Profile getProfile(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        Profile profile = (Profile) criteria.uniqueResult();
        if (profile != null) {
            Hibernate.initialize(profile.getProfileMedias());
        }
        return profile;
    }

    @Override
    public Profile getProfile(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("name", name).ignoreCase());
        Profile profile = (Profile) criteria.uniqueResult();
        if (profile != null) {
            Hibernate.initialize(profile.getProfileMedias());
        }
        return profile;
    }

    @Override
    public Profile addProfile(Profile profile) {
        try {
            for(ProfileMedia profileMedia: profile.getProfileMedias()){
                profileMedia.setProfile(profile);
            }
            persist(profile);
        } catch (Exception ex) {
            Logger.ERROR("1- Error AdminPassesDao 4 on API [" + ex.getMessage() + "]", profile, "");
        }
        return profile;
    }

}
