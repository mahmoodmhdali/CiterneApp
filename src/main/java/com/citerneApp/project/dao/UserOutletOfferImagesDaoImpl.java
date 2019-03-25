package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserOutletOfferImages;
import org.springframework.stereotype.Repository;

@Repository("userOutletOfferImagesDao")
public class UserOutletOfferImagesDaoImpl extends AbstractDao<Long, UserOutletOfferImages> implements UserOutletOfferImagesDao {

    @Override
    public void deleteImage(UserOutletOfferImages image) {
        try {
            delete(image);
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserOutletOfferImagesDao 1 on API [" + ex.getMessage() + "]", image, "");
        }
    }
}
