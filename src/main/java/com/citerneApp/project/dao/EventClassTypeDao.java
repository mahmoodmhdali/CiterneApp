package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassType;
import java.util.List;

public interface EventClassTypeDao {

    List<EventClassType> getEventClassTypees();

    EventClassType getEventClassType(Long id);

}
