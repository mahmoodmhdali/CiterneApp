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

@Repository("profileMediaDao")
public class ProfileMediaDaoImpl extends AbstractDao<Long, ProfileMedia> implements ProfileMediaDao {

    @Override
    public void deleteProfileMediasForProfile(Long profileID) {
        Criteria criteria = createEntityCriteria();
        criteria.createAlias("profile", "profile");
        criteria.add(Restrictions.eq("profile.id", profileID));
        List<ProfileMedia> profileMedias = (List<ProfileMedia>) criteria.list();
        for (ProfileMedia profileMedia : profileMedias) {
            delete(profileMedia);
        }
    }

}
