package com.citerneApp.project.service;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.api.commons.utils.QRCodeGenerator;
import com.citerneApp.api.commons.utils.SessionUtils;
import com.citerneApp.api.commons.utils.Utils;
import com.citerneApp.api.engine.SettingsEngine;
import com.citerneApp.project.dao.AuditTrailDao;
import com.citerneApp.project.dao.UserDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.helpermodel.UserProfilePasswordValidator;
import com.citerneApp.project.helpermodel.UsersPagination;
import com.citerneApp.project.model.AuditTrail;
import com.citerneApp.project.model.Group;
import com.citerneApp.project.model.Language;
import com.citerneApp.project.model.UserAttempt;
import com.citerneApp.project.model.UserProfile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.mail.internet.AddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    UserAttemptService userAttemptService;

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SessionUtils sessionUtils;

    @Autowired
    GroupService groupService;

    @Autowired
    LanguageService languageService;

    @Autowired
    SettingsEngine settingsEngine;

    @Autowired
    QRCodeGenerator qRCodeGenerator;

    @Autowired
    AuditTrailDao auditTrailDao;

    @Override
    public List<UserProfile> getUsers(Long excludeLoggedInUserID, Integer type) {
        return userDao.getUsers(excludeLoggedInUserID, type);
    }

    @Override
    public List<UserProfile> getOutletUsers() {
        return userDao.getOutletUsers();
    }

    @Override
    public List<UserProfile> getCompanyUsers() {
        return userDao.getCompanyUsers();
    }

    @Override
    public UsersPagination getUsersPagination(Long excludeLoggedInUserID, Integer type, int pageNumber, int maxRes) {
        return userDao.getUsersPagination(excludeLoggedInUserID, type, pageNumber, maxRes);
    }

    @Override
    public ResponseBodyEntity getUser(Long id) {
        UserProfile userProfle = userDao.getUser(id);
        if (userProfle == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription("User Not Found")
                    .getResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", userProfle)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity getUser(String email) {
        UserProfile userProfle = userDao.getUser(email.toLowerCase());
        if (userProfle == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription("User Not Found")
                    .getResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", userProfle)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity getUserByToken(String token) {
        UserProfile userProfle = userDao.getUserByToken(token);
        if (userProfle == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription("User Token Not Found")
                    .getResponse();
        }
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", userProfle)
                .getResponse();
    }

    @Override
    public List<UserProfile> filterUsersByGroup(Long groupId) {
        return userDao.filterUsersByGroup(groupId);
    }

    @Override
    public UserProfile toUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public UserProfile toUser(String email) {
        return userDao.getUser(email);
    }

    @Override
    public ResponseBodyEntity addUser(UserProfile user) throws AddressException {
        user.setEnabled(true);
        user.setCreatedDate(new Date());
        UserProfile persistantUser = userDao.getUser(user.getEmail());
        if (persistantUser != null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("email", "Email already taken")
                    .getResponse();
        }

        user.setEmail(user.getEmail().trim().toLowerCase());
        user.setName(user.getName().trim().toLowerCase());
        /* Set UserLoginAttemps */
        UserAttempt userAttempt = new UserAttempt();
        userAttempt.setAttempt(0);
        userAttempt.setLastModified(new Date());
        user.setUserAttempt(userAttempt);
        userAttempt.setUserProfileId(user);
        user.setLanguage(languageService.getLanguage(Long.parseLong("1")));
        Collection<Group> groupCollection = new ArrayList<>();
        if (null != user.getType()) {
            switch (user.getType()) {
                case 1: {
                    Group group = groupService.toGroupForAdd(9L);
                    groupCollection.add(group);
                    user.setJobTitle("Citerne Admin");
                    break;
                }
                case 2: {
                    Group group = groupService.toGroupForAdd(10L);
                    groupCollection.add(group);
                    user.setJobTitle("Citerne User");
                    break;
                }
                case 99: {
                    Group group = groupService.toGroupForAdd(3L);
                    groupCollection.add(group);
                    user.setJobTitle("Our System User");
                    break;
                }
                default:
                    break;
            }
        }
        user.setGroupCollection(groupCollection);
        user.setEnabled(false);
        user.setPassword("NewUserCreated");
        String token = Utils.generateToken();
        user.setResetPasswordToken(token);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 3);
        user.setResetPasswordTokenValidity(c.getTime());
        try {
            UserProfile loggedInUser = this.getAuthenticatedUser();
            if (loggedInUser != null) {
                AuditTrail auditTrail = new AuditTrail();
                auditTrail.setActionDate(new Date());
                auditTrail.setActionID(1L);
                auditTrail.setDescription("Add User with email email=" + user.getEmail());
                auditTrail.setUserProfile(loggedInUser);
                auditTrailDao.addAuditTrail(auditTrail);
            }
            userDao.addUser(user);
        } catch (Exception ex) {
            Logger.ERROR("1- Error addUser 1 on API [" + ex.getMessage() + "]", user, "");
        }
        try {
            String[] email = {user.getEmail()};
            String webURL = (String) settingsEngine.getFirstLevelSetting("WEB_URL");
            sendEmail("Enter this pin in Citerne App app to activate your account\nPIN: " + token + "\nOr follow the below link\n" + webURL + token, email, "Account Created");
        } catch (Exception ex) {
            Logger.ERROR("1- Error addUser 2 on API [" + ex.getMessage() + "]", user.getEmail(), "");
        }
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", user)
                .getResponse();
    }

    @Override
    public void sendEmailAndUpdateToken(String email) throws AddressException {
        try {
            UserProfile user = userDao.getUser(email);
            if (user != null) {
                String token = Utils.generateToken();
                user.setResetPasswordToken(token);
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DATE, 3);
                user.setResetPasswordTokenValidity(c.getTime());
                String[] emails = {user.getEmail()};
                String webURL = (String) settingsEngine.getFirstLevelSetting("WEB_URL");
                sendEmail("Enter this pin in citerne App app to activate your account\nPIN: " + token + "\nOr follow the below link\n" + webURL + token, emails, "Account Created");
            }
        } catch (Exception ex) {
            Logger.ERROR("1- Error sendEmailAndUpdateToken 1 on API [" + ex.getMessage() + "]", email, "");
        }
    }

    @Override
    public ResponseBodyEntity updateUser(UserProfile user) {
        UserProfile persistantUser = userDao.getUser(user.getId());

        if (persistantUser == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.unknownOrDeleted", null))
                    .getResponse();
        }

        // Check if user email is unique
        if (!persistantUser.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())) {
            if (userDao.getUser(user.getEmail()) != null) {
                return ResponseBuilder.getInstance()
                        .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                        .addHttpResponseEntityData("email", this.getMessageBasedOnLanguage("user.emailTaken", null))
                        .getResponse();
            }
        }

        persistantUser.setName(user.getName());
        persistantUser.setEmail(user.getEmail().toLowerCase());
        persistantUser.setLanguage(languageService.getLanguage(Long.parseLong("1")));

        if (null != persistantUser.getType()) {
            switch (persistantUser.getType()) {
                case 0: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 99: {
                    break;
                }
                default:
                    break;
            }
        }

        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", persistantUser)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity deleteUser(Long id) {

        UserProfile persistantUser = userDao.getUser(id);
        if (persistantUser == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription("User Not Found")
                    .getResponse();
        }

        // Forbid login user from deleting his account
        if (Objects.equals(((UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), persistantUser.getId())) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ALERT)
                    .addHttpResponseEntityData("user", this.getMessageBasedOnLanguage("user.cantDelete", null))
                    .getResponse();
        }

        // Prohibit deletion of default users 
        if (id == 1 || id == 2) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ALERT)
                    .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.defaultUsers", null))
                    .getResponse();
        }

        if (persistantUser.getDeletedDate() != null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.alreadyDeleted", null))
                    .getResponse();
        }
        persistantUser.setDeletedDate(new Date());

        UserProfile loggedInUser = this.getAuthenticatedUser();
        if (loggedInUser != null) {
            AuditTrail auditTrail = new AuditTrail();
            auditTrail.setActionDate(new Date());
            auditTrail.setActionID(2L);
            auditTrail.setDescription("Delete User with email email=" + persistantUser.getEmail());
            auditTrail.setUserProfile(loggedInUser);
            auditTrailDao.addAuditTrail(auditTrail);
        }
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.deletedSuccess", null))
                .getResponse();
    }

    @Override
    public void setAccountLocked(String email, boolean locked) {
        UserProfile user = userDao.getUser(email.toLowerCase());
        if (user != null) {
            user.setAccountLocked(locked);
        }
    }

    @Override
    public UserAttempt getUserAttemptCollection(String email) {
        UserAttempt userAttempt = null;
        UserProfile user = userDao.getUser(email.toLowerCase());
        if (user != null) {
            userAttempt = user.getUserAttempt();
        }
        return userAttempt;
    }

    @Override
    public boolean isUniqueFromOther(UserProfile userProfile) {
        UserProfile user = userDao.getUser(userProfile.getEmail().toLowerCase());
        if (user == null) {
            return true;
        } else if (!user.getId().equals(userProfile.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public ResponseBodyEntity updateUserLanguage(Long languageId) {
        UserProfile persistantUser = getAuthenticatedUser();
        Language language = languageService.getLanguage(languageId);
        persistantUser.setLanguage(language);
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", persistantUser)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity updateUserSettings(UserProfile user) {
        UserProfile persistantUser = userDao.getUser(getAuthenticatedUser().getId());

        if (persistantUser == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.unknownOrDeleted", null))
                    .getResponse();
        }
        persistantUser.setName(user.getName());
        return ResponseBuilder.getInstance()
                .setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", persistantUser)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity changeUserPassword(UserProfile user, UserProfilePasswordValidator userProfilePasswordValidator) {

        UserProfile persisteUser = userService.toUser(getAuthenticatedUser().getId());
        // Check if mobile number is unique
        if (!passwordEncoder.matches(user.getPassword(), persisteUser.getPassword())) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.PARAMETERS_VALIDATION_ERROR)
                    .addHttpResponseEntityData("password", "Incorret Password")
                    .getResponse();
        }

        persisteUser.setPassword(passwordEncoder.encode(userProfilePasswordValidator.getNewPassword()));

        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", persisteUser)
                .getResponse();
    }

    @Override
    public ResponseBodyEntity changeUserPasswordByToken(String token, UserProfilePasswordValidator userProfilePasswordValidator) {

        UserProfile persistantUser = userDao.getUserByToken(token);
        if (persistantUser == null) {
            return ResponseBuilder.getInstance()
                    .setHttpResponseEntityResultCode(ResponseCode.ENTITY_NOT_FOUND)
                    .setHttpResponseEntityResultDescription(this.getMessageBasedOnLanguage("user.unknownOrDeleted", null))
                    .getResponse();
        }

        persistantUser.setPassword(passwordEncoder.encode(userProfilePasswordValidator.getNewPassword()));
        persistantUser.setEnabled(true);
        persistantUser.setResetPasswordToken(null);
        persistantUser.setResetPasswordTokenValidity(null);

        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("user", persistantUser)
                .getResponse();
    }

}
