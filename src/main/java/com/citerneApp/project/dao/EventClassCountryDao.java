package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCountry;
import java.util.List;

public interface EventClassCountryDao {

    List<EventClassCountry> getEventClassCountryes();

    EventClassCountry getEventClassCountry(Long id);

}
