package com.citerneApp.project.service;

import com.citerneApp.project.model.UserOutletInfo;
import java.util.List;

public interface UserOutletInfoService {

    List<UserOutletInfo> getUserOutletInfos();

    List<UserOutletInfo> getUserOutletInfosByCategory(Long id);

    UserOutletInfo getUserOutletInfo(Long id);

}
