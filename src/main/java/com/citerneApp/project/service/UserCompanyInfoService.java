package com.citerneApp.project.service;

import com.citerneApp.project.model.UserCompanyInfo;
import java.util.List;

public interface UserCompanyInfoService {

    List<UserCompanyInfo> getUserCompanyInfos();

    UserCompanyInfo getUserCompanyInfo(Long id);

}
