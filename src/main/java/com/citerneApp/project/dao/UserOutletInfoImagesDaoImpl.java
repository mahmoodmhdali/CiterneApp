package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserOutletInfoImages;
import org.springframework.stereotype.Repository;

@Repository("userOutletInfoImagesDao")
public class UserOutletInfoImagesDaoImpl extends AbstractDao<Long, UserOutletInfoImages> implements UserOutletInfoImagesDao {

    @Override
    public void deleteImage(UserOutletInfoImages image) {
        try {
            delete(image);
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserOutletInfoImagesDao 1 on API [" + ex.getMessage() + "]", image, "");
        }
    }
}
