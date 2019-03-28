package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassSchedule;
import java.util.List;

public interface EventClassScheduleService {

    List<EventClassSchedule> getEventClassSchedulees();

    EventClassSchedule getEventClassSchedule(Long id);

}
