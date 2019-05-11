package com.citerneApp.api.engine;

import com.citerneApp.project.model.UserAttempt;
import com.citerneApp.project.service.EventClassService;
import com.citerneApp.project.service.UserAttemptService;
import com.citerneApp.project.service.UserService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventClassEngine {

    @Autowired
    EventClassService eventClassService;

    @Scheduled(cron = "0 0 2 * * ?")
    public synchronized void init() {
        eventClassService.getEventClassesForMidnightCheck();
    }

}
