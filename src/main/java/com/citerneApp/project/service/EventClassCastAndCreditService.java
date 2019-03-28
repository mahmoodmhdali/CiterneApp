package com.citerneApp.project.service;

import com.citerneApp.project.model.EventClassCastAndCredit;
import java.util.List;

public interface EventClassCastAndCreditService {

    List<EventClassCastAndCredit> getEventClassCastAndCredites();

    EventClassCastAndCredit getEventClassCastAndCredit(Long id);

}
