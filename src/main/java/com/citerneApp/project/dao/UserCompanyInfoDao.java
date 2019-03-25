package com.citerneApp.project.dao;

import com.citerneApp.project.model.UserCompanyInfo;
import java.util.List;

public interface UserCompanyInfoDao {

    List<UserCompanyInfo> getUserCompanyInfos();

    UserCompanyInfo getUserCompanyInfo(Long id);
}
