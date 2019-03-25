package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.helpermodel.UserPassPurchasedPagination;
import com.citerneApp.project.model.UserPassPurchased;
import java.util.List;

public interface UserPassPurchasedService {

    List<UserPassPurchased> getUserPassPurchaseds();

    UserPassPurchased getUserPassPurchased(Long id);

    UserPassPurchasedPagination getUserPassPurchasedPagination(int pageNumber, int maxRes);

    ResponseBodyEntity addUserPassPurchased(UserPassPurchased userPassPurchased, Long packageId);

    ResponseBodyEntity editUserPassPurchased(UserPassPurchased userPassPurchased);

}
