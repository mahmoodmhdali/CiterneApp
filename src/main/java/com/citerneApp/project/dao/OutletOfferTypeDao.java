package com.citerneApp.project.dao;

import com.citerneApp.project.model.OutletOfferType;
import java.util.List;

public interface OutletOfferTypeDao {

    List<OutletOfferType> getOutletOfferTypes();

    OutletOfferType getOutletOfferType(Long id);

}
