package com.citerneApp.project.service;

import com.citerneApp.project.dao.UserCompanyPassesDao;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.ResponseBuilder;
import com.citerneApp.project.helpermodel.ResponseCode;
import com.citerneApp.project.helpermodel.UserCompanyPassPagination;
import com.citerneApp.project.model.UserCompanyPasses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userCompanyPassesService")
@Transactional
public class UserCompanyPassesServiceImpl extends AbstractService implements UserCompanyPassesService {

    @Autowired
    UserCompanyPassesDao userCompanyPassesDao;

    @Override
    public List<UserCompanyPasses> getUserCompanyPasses() {
        return userCompanyPassesDao.getUserCompanyPasses();
    }

    @Override
    public List<UserCompanyPasses> getUserCompanyPassesByCompanyUserId(Long id) {
        return userCompanyPassesDao.getUserCompanyPassesByCompanyUserId(id);
    }

    @Override
    public UserCompanyPasses getUserCompanyPasse(Long id) {
        return userCompanyPassesDao.getUserCompanyPasse(id);
    }

    @Override
    public UserCompanyPassPagination getUserCompanyPassesPagination(int pageNumber, int maxRes) {
        return userCompanyPassesDao.getUserCompanyPassesPagination(pageNumber, maxRes);
    }

    @Override
    public ResponseBodyEntity addUserCompanyPasses(UserCompanyPasses userCompanyPasses) {
        userCompanyPasses.setRemainingUsers(userCompanyPasses.getNumberOfUsers());
        userCompanyPassesDao.addUserCompanyPasses(userCompanyPasses);
        return ResponseBuilder.getInstance().
                setHttpResponseEntityResultCode(ResponseCode.SUCCESS)
                .addHttpResponseEntityData("userCompanyPass", "Company package added successfully")
                .getResponse();
    }

}
