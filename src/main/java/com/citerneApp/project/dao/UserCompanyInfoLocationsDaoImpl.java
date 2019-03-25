package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserCompanyInfoLocations;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository("userCompanyInfoLocationsDao")
public class UserCompanyInfoLocationsDaoImpl extends AbstractDao<Long, UserCompanyInfoLocations> implements UserCompanyInfoLocationsDao {

    @Override
    public void deleteLocations(Collection<UserCompanyInfoLocations> locations) {
        for (UserCompanyInfoLocations location : locations) {
            try {
                delete(location);
            } catch (Exception ex) {
                Logger.ERROR("1- Error UserCompanyInfoLocationsDao 1 on API [" + ex.getMessage() + "]", location, "");
            }
        }
    }
}
