package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.UserCompanyPassPagination;
import com.citerneApp.project.model.UserCompanyPasses;
import java.util.List;

public interface UserCompanyPassesService {

    List<UserCompanyPasses> getUserCompanyPasses();

    List<UserCompanyPasses> getUserCompanyPassesByCompanyUserId(Long id);

    UserCompanyPasses getUserCompanyPasse(Long id);

    UserCompanyPassPagination getUserCompanyPassesPagination(int pageNumber, int maxRes);

    ResponseBodyEntity addUserCompanyPasses(UserCompanyPasses userCompanyPasses);

}
