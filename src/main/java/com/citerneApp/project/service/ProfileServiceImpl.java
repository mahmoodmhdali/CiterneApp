package com.citerneApp.project.service;

import com.citerneApp.project.dao.ProfileDao;
import com.citerneApp.project.model.Profile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("profileService")
@Transactional
public class ProfileServiceImpl extends AbstractService implements ProfileService {

    @Autowired
    ProfileDao profileDao;

    @Override
    public List<Profile> getProfilees() {
        return profileDao.getProfilees();
    }

    @Override
    public Profile getProfile(Long id) {
        return profileDao.getProfile(id);
    }

}
