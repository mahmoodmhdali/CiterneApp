package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.ProfilesPagination;
import com.citerneApp.project.model.Profile;
import java.util.List;

public interface ProfileMediaDao {

    void deleteProfileMediasForProfile(Long profileID);

}
