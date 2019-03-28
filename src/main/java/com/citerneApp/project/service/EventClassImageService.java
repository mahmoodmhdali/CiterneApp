package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassImage;
import java.util.List;

public interface EventClassImageService {

    List<EventClassImage> getEventClassImagees();

    EventClassImage getEventClassImage(Long id);

}
