package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassMedia;
import java.util.List;

public interface EventClassMediaDao {

    List<EventClassMedia> getEventClassMediaes();

    EventClassMedia getEventClassMedia(Long id);

    void deleteEventClassMedia(Long eventID);

}
