package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.UserProfilePasswordValidator;
import com.citerneApp.project.helpermodel.UsersPagination;
import com.citerneApp.project.model.UserAttempt;
import com.citerneApp.project.model.UserProfile;
import java.util.List;
import javax.mail.internet.AddressException;

public interface UserService {

    List<UserProfile> getUsers(Long excludeLoggedInUserID, Integer type);

    List<UserProfile> getOutletUsers();

    List<UserProfile> getCompanyUsers();

    UsersPagination getUsersPagination(Long excludeLoggedInUserID, Integer type, Long headID, int pageNumber, int maxRes);

    ResponseBodyEntity getUser(Long id);

    ResponseBodyEntity getUser(String email);

    ResponseBodyEntity getUserByToken(String token);

    void sendEmailAndUpdateToken(String email) throws AddressException;

    List<UserProfile> filterUsersByGroup(Long groupId);

    UserProfile toUser(Long id);

    UserProfile toUser(String email);

    ResponseBodyEntity addUser(UserProfile user) throws AddressException;

    ResponseBodyEntity updateUser(UserProfile user);

    ResponseBodyEntity updateUserSettings(UserProfile user);

    ResponseBodyEntity changeUserPassword(UserProfile user, UserProfilePasswordValidator userProfilePasswordValidator);

    ResponseBodyEntity changeUserPasswordByToken(String token, UserProfilePasswordValidator userProfilePasswordValidator);

    ResponseBodyEntity deleteUser(Long id);

    boolean isUniqueFromOther(UserProfile userProfile);

    void setAccountLocked(String email, boolean locked);

    UserAttempt getUserAttemptCollection(String email);

    ResponseBodyEntity updateUserLanguage(Long languageId);

}
