package com.citerneApp.project.dao;

import com.citerneApp.project.model.UserOutletInfo;
import java.util.List;

public interface UserOutletInfoDao {

    List<UserOutletInfo> getUserOutletInfos();

    List<UserOutletInfo> getUserOutletInfosByCategory(Long id);

    UserOutletInfo getUserOutletInfo(Long id);
}
