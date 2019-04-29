package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.UsersPagination;
import com.citerneApp.project.model.UserProfile;
import java.util.List;

public interface UserDao {

    List<UserProfile> getUsers(Long excludeLoggedInUserID, Integer type);

    List<UserProfile> getOutletUsers();

    List<UserProfile> getCompanyUsers();

    UsersPagination getUsersPagination(Long excludeLoggedInUserID, Integer type, int pageNumber, int maxRes);

    UserProfile getUser(Long id);

    UserProfile getUserByToken(String token);

    UserProfile getUser(String email);

    UserProfile filterByMobileNumber(String mobileNumber);

    List<UserProfile> filterUsersByGroup(Long groupId);

    void addUser(UserProfile user);

    void deleteUser(UserProfile user);
}
