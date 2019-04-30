package com.citerneApp.project.service;

import com.citerneApp.project.model.Profile;
import java.util.List;

public interface ProfileService {

    List<Profile> getProfilees();

    Profile getProfile(Long id);

    Profile getProfile(String name);

}
