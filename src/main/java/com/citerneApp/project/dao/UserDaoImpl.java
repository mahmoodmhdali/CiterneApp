package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.helpermodel.UsersPagination;
import com.citerneApp.project.model.Group;
import com.citerneApp.project.model.UserProfile;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, UserProfile> implements UserDao {

    @Override
    public List<UserProfile> getUsers(Long excludeLoggedInUserID, Integer type) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.addOrder(Order.asc("name"));
            criteria.add(Restrictions.ne("id", excludeLoggedInUserID)); // To avoid including logged in user
            criteria.add(Restrictions.ne("type", 99));
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            List<UserProfile> users = (List<UserProfile>) criteria.list();
            for (UserProfile user : users) {
                Hibernate.initialize(user.getGroupCollection());
            }
            return users;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public List<UserProfile> getOutletUsers() {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.addOrder(Order.asc("name"));
            criteria.add(Restrictions.eq("type", 2));
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            List<UserProfile> users = (List<UserProfile>) criteria.list();
            for (UserProfile user : users) {
                Hibernate.initialize(user.getGroupCollection());
            }
            return users;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 2 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public List<UserProfile> getCompanyUsers() {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.addOrder(Order.asc("name"));
            criteria.add(Restrictions.eq("type", 1));
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            List<UserProfile> users = (List<UserProfile>) criteria.list();
            for (UserProfile user : users) {
                Hibernate.initialize(user.getGroupCollection());
            }
            return users;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 3 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public UsersPagination getUsersPagination(Long excludeLoggedInUserID, Integer type, int pageNumber, int maxRes) {
        try {
            Criteria criteria = createEntityCriteria();
            if (type == 1) {
                criteria.add(Restrictions.eq("type", 1));
            } else {
                criteria.add(Restrictions.eq("type", 2));
            }
            criteria.addOrder(Order.asc("name"));
            criteria.add(Restrictions.ne("id", excludeLoggedInUserID)); // To avoid including logged in user
            criteria.add(Restrictions.isNull("deletedDate"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            criteria.setProjection(Projections.rowCount());
            Number totalResults = (Number) criteria.uniqueResult();
            criteria.setProjection(null);
            criteria.setResultTransformer(Criteria.ROOT_ENTITY);
            criteria.setFirstResult((pageNumber - 1) * maxRes);
            criteria.setMaxResults(maxRes);
            List<UserProfile> users = (List<UserProfile>) criteria.list();
            int currentPage = pageNumber;
            int maxPages = (int) Math.ceil((double) ((double) totalResults.intValue() / (double) maxRes));
            UsersPagination usersPagination = new UsersPagination(maxPages, currentPage, totalResults.intValue(), users);
            return usersPagination;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 4 on API [" + ex.getMessage() + "]", "Exclued: " + excludeLoggedInUserID + ". Type: " + type + ". Page number: " + pageNumber + ". Max result: " + maxRes, "");
        }
        return null;
    }

    @Override
    public UserProfile getUser(Long id) {
        try {
            UserProfile user = getByKey(id);
            if (user == null) {
                return null;
            }
            if (user.getDeletedDate() != null) {
                return null;
            }
            Hibernate.initialize(user.getAuthorities());
            Hibernate.initialize(user.getGroupCollection());
            Hibernate.initialize(user.getLanguage());
            for (Group group : user.getGroupCollection()) {
                Hibernate.initialize(group.getRoleCollection());
            }
            return user;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 5 on API [" + ex.getMessage() + "]", id, "");
        }
        return null;
    }

    @Override
    public UserProfile getUser(String email) {
        try {
            Criteria criteria = createEntityCriteria()
                    .add(Restrictions.eq("email", email))
                    .add(Restrictions.isNull("deletedDate"));
            UserProfile user = (UserProfile) criteria.uniqueResult();
            if (user != null) {
                Hibernate.initialize(user.getAuthorities());
                Hibernate.initialize(user.getGroupCollection());
                Hibernate.initialize(user.getLanguage());
                for (Group group : user.getGroupCollection()) {
                    Hibernate.initialize(group.getRoleCollection());
                }
            }
            return user;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 6 on API [" + ex.getMessage() + "]", email, "");
        }
        return null;
    }

    @Override
    public UserProfile getUserByToken(String token) {
        try {
            Criteria criteria = createEntityCriteria()
                    .add(Restrictions.eq("resetPasswordToken", token))
                    .add(Restrictions.ge("resetPasswordTokenValidity", new Date()))
                    .add(Restrictions.isNull("deletedDate"));
            UserProfile user = (UserProfile) criteria.uniqueResult();
            return user;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 7 on API [" + ex.getMessage() + "]", token, "");
        }
        return null;
    }

    @Override
    public UserProfile filterByMobileNumber(String mobileNumber) {
        try {
            Criteria criteria = createEntityCriteria()
                    .add(Restrictions.eq("mobileNumber", mobileNumber))
                    .add(Restrictions.isNull("deletedDate"));
            return (UserProfile) criteria.uniqueResult();
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 8 on API [" + ex.getMessage() + "]", mobileNumber, "");
        }
        return null;
    }

    @Override
    public List<UserProfile> filterUsersByGroup(Long groupId) {
        try {
            Criteria criteria = createEntityCriteria()
                    .createAlias("groupCollection", "groups")
                    .add(Restrictions.eq("groups.id", groupId))
                    .add(Restrictions.isNull("deletedDate"))
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);  // To avoid duplicates.
            return (List<UserProfile>) criteria.list();
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 9 on API [" + ex.getMessage() + "]", groupId, "");
        }
        return null;
    }

    @Override
    public void addUser(UserProfile user) {
        try {
            save(user);
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 10 on API [" + ex.getMessage() + "]", user, "");
        }
    }

    @Override
    public void deleteUser(UserProfile user) {
        try {
            delete(user);
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserDao 11 on API [" + ex.getMessage() + "]", user, "");
        }
    }

}
