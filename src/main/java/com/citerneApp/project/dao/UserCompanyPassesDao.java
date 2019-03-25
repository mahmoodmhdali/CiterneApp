package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.UserCompanyPassPagination;
import com.citerneApp.project.model.UserCompanyPasses;
import java.util.List;

public interface UserCompanyPassesDao {

    List<UserCompanyPasses> getUserCompanyPasses();
    
    List<UserCompanyPasses> getUserCompanyPassesByCompanyUserId(Long id);

    UserCompanyPasses getUserCompanyPasse(Long id);

    UserCompanyPassPagination getUserCompanyPassesPagination(int pageNumber, int maxRes);

    void addUserCompanyPasses(UserCompanyPasses userCompanyPasses);
}
