package com.citerneApp.project.dao;

import com.citerneApp.api.commons.Logger;
import com.citerneApp.project.model.UserCompanyInfoImages;
import org.springframework.stereotype.Repository;

@Repository("userCompanyInfoImagesDao")
public class UserCompanyInfoImagesDaoImpl extends AbstractDao<Long, UserCompanyInfoImages> implements UserCompanyInfoImagesDao {

    @Override
    public void deleteImage(UserCompanyInfoImages image) {
        try {
            delete(image);
        } catch (Exception ex) {
            Logger.ERROR("1- Error UserCompanyInfoImagesDao 1 on API [" + ex.getMessage() + "]", image, "");
        }
    }
}
