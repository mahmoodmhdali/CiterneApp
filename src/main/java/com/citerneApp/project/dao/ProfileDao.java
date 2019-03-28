package com.citerneApp.project.dao;

import com.citerneApp.project.model.Profile;
import java.util.List;

public interface ProfileDao {

    List<Profile> getProfilees();

    Profile getProfile(Long id);

}
