package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassImage;
import java.util.List;

public interface EventClassImageDao {

    List<EventClassImage> getEventClassImagees();

    EventClassImage getEventClassImage(Long id);

}
