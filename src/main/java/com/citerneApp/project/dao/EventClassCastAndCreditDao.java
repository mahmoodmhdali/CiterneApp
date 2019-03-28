package com.citerneApp.project.dao;

import com.citerneApp.project.model.EventClassCastAndCredit;
import java.util.List;

public interface EventClassCastAndCreditDao {

    List<EventClassCastAndCredit> getEventClassCastAndCredites();

    EventClassCastAndCredit getEventClassCastAndCredit(Long id);

}
