package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassType;
import java.util.List;

public interface EventClassTypeService {

    List<EventClassType> getEventClassTypees();

    EventClassType getEventClassType(Long id);

}
