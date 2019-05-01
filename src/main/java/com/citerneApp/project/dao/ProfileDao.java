package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.ProfilesPagination;
import com.citerneApp.project.model.Profile;
import java.util.List;

public interface ProfileDao {

    List<Profile> getProfilees();

    Profile getProfile(Long id);

    Profile getProfile(String name);

    Profile addProfile(Profile profile);

    ProfilesPagination getProfilesPagination(int pageNumber, int maxRes);

}
