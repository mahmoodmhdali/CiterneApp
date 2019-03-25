package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.UserPassPurchasedPagination;
import com.citerneApp.project.model.UserPassPurchased;
import java.util.List;

public interface UserPassPurchasedDao {

    List<UserPassPurchased> getUserPassPurchaseds();

    UserPassPurchased getUserPassPurchased(Long id);

    UserPassPurchasedPagination getUserPassPurchasedPagination(int pageNumber, int maxRes);

    void addUserPassPurchased(UserPassPurchased userPassPurchased);
}
