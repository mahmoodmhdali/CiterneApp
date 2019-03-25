package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserOutletInfoLocations;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository("userOutletInfoLocationsDao")
public class UserOutletInfoLocationsDaoImpl extends AbstractDao<Long, UserOutletInfoLocations> implements UserOutletInfoLocationsDao {

    @Override
    public void deleteLocations(Collection<UserOutletInfoLocations> locations) {
        for (UserOutletInfoLocations location : locations) {
            try {
                delete(location);
            } catch (Exception ex) {
                Logger.ERROR("1- Error UserOutletInfoLocationsDao 1 on API [" + ex.getMessage() + "]", location, "");
            }
        }
    }
}
