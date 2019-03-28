package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassCategory;
import java.util.List;

public interface EventClassCategoryService {

    List<EventClassCategory> getEventClassCategoryes();

    EventClassCategory getEventClassCategory(Long id);

}
