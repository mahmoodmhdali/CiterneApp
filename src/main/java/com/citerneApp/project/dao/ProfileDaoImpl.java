package com.citerneApp.project.dao;

import com.citerneApp.project.model.Profile;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("profileDao")
public class ProfileDaoImpl extends AbstractDao<Long, Profile> implements ProfileDao {

    @Override
    public List<Profile> getProfilees() {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        List<Profile> profilees = (List<Profile>) criteria.list();
        return profilees;
    }

    @Override
    public Profile getProfile(Long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("id", id));
        Profile profile = (Profile) criteria.uniqueResult();
        Hibernate.initialize(profile.getProfileMedias());
        return profile;
    }

    @Override
    public Profile getProfile(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.isNull("deletedDate"));
        criteria.add(Restrictions.eq("name", name));
        Profile profile = (Profile) criteria.uniqueResult();
        Hibernate.initialize(profile.getProfileMedias());
        return profile;
    }

}
