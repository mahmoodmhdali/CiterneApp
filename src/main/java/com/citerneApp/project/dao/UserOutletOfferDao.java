package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.OffersPagination;
import com.citerneApp.project.model.UserOutletOffer;
import java.util.List;

public interface UserOutletOfferDao {

    List<UserOutletOffer> getUserOutletOffers();

    UserOutletOffer getUserOutletOffer(Long id);

    UserOutletOffer getUserOutletOfferByName(String name);

    List<UserOutletOffer> getUserOutletOffersByType(Long type);

    List<UserOutletOffer> getUserOutletOffersByOutletId(Long id);

    OffersPagination getOffersPagination(int pageNumber, int maxRes);

    void addUser(UserOutletOffer userOutletOffer);
}
