package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassSchedule;
import java.util.List;

public interface EventClassScheduleDao {

    List<EventClassSchedule> getEventClassSchedulees();

    EventClassSchedule getEventClassSchedule(Long id);

    void deleteEventClassSchedule(Long eventID);

}
