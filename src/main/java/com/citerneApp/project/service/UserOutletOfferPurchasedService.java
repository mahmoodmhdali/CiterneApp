package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.UserOutletOfferPurchased;
import java.util.List;

public interface UserOutletOfferPurchasedService {

    List<UserOutletOfferPurchased> getUserOutletOfferPurchaseds();

    UserOutletOfferPurchased getUserOutletOfferPurchased(Long id);

    UserOutletOfferPurchased getUserOutletOfferPurchasedByOfferIDAndUserID(Long offerID, Long userID);

    ResponseBodyEntity addUserOutletOfferPurchased(Long offerID, Long userID);

}
