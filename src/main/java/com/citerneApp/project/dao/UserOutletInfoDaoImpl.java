package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserOutletInfo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userOutletInfoDao")
public class UserOutletInfoDaoImpl extends AbstractDao<Long, UserOutletInfo> implements UserOutletInfoDao {

    @Override
    public List<UserOutletInfo> getUserOutletInfos() {
        try {
            Criteria criteria = createEntityCriteria()
                    .add(Restrictions.isNull("deletedDate"));
            List<UserOutletInfo> userOutletInfos = (List<UserOutletInfo>) criteria.list();
            for (UserOutletInfo userOutletInfo : userOutletInfos) {
                Hibernate.initialize(userOutletInfo.getOutletCategoryCollection());
                Hibernate.initialize(userOutletInfo.getUserOutletInfoImagesCollection());
                Hibernate.initialize(userOutletInfo.getUserOutletInfoLocationsCollection());
//            Hibernate.initialize(userOutletInfo.getUserOutletOffers());
//            for (UserOutletOffer userOutletOffer : userOutletInfo.getUserOutletOffers()) {
//                Hibernate.initialize(userOutletOffer.getOutletOfferType());
//            }
            }
            return userOutletInfos;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserOutletDao 1 on API [" + ex.getMessage() + "]", "", "");
        }
        return null;
    }

    @Override
    public List<UserOutletInfo> getUserOutletInfosByCategory(Long id) {
        try {
            Criteria criteria = createEntityCriteria()
                    .createAlias("outletCategoryCollection", "outletCategory")
                    .add(Restrictions.eq("outletCategory.id", id))
                    .add(Restrictions.isNull("deletedDate"));
            List<UserOutletInfo> userOutletInfos = (List<UserOutletInfo>) criteria.list();
            for (UserOutletInfo userOutletInfo : userOutletInfos) {
                Hibernate.initialize(userOutletInfo.getOutletCategoryCollection());
                Hibernate.initialize(userOutletInfo.getUserOutletInfoImagesCollection());
                Hibernate.initialize(userOutletInfo.getUserOutletInfoLocationsCollection());
            }
            return userOutletInfos;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserOutletDao 2 on API [" + ex.getMessage() + "]", id, "");
        }
        return null;
    }

    @Override
    public UserOutletInfo getUserOutletInfo(Long id) {
        try {
            UserOutletInfo userOutletInfo = getByKey(id);
            if (userOutletInfo == null || userOutletInfo.getDeletedDate() != null) {
                return null;
            }
            Hibernate.initialize(userOutletInfo.getOutletCategoryCollection());
            Hibernate.initialize(userOutletInfo.getUserOutletInfoImagesCollection());
            Hibernate.initialize(userOutletInfo.getUserOutletInfoLocationsCollection());
            return userOutletInfo;
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserOutletDao 3 on API [" + ex.getMessage() + "]", id, "");
        }
        return null;
    }

}
