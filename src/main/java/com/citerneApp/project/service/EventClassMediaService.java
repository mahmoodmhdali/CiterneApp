package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassMedia;
import java.util.List;

public interface EventClassMediaService {

    List<EventClassMedia> getEventClassMediaes();

    EventClassMedia getEventClassMedia(Long id);

}
