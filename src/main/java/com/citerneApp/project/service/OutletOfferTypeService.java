package com.citerneApp.project.service;

import com.citerneApp.project.model.OutletOfferType;
import java.util.List;

public interface OutletOfferTypeService {

    List<OutletOfferType> getOutletOfferTypes();

    OutletOfferType getOutletOfferType(Long id);

}
