package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCategory;
import java.util.List;

public interface EventClassCategoryDao {

    List<EventClassCategory> getEventClassCategoryes();

    EventClassCategory getEventClassCategory(Long id);

}
