package com.citerneApp.project.service;

import com.citerneApp.project.dao.EventClassCastAndCreditDao;
import com.citerneApp.project.model.EventClassCastAndCredit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eventClassCastAndCreditService")
@Transactional
public class EventClassCastAndCreditServiceImpl extends AbstractService implements EventClassCastAndCreditService {

    @Autowired
    EventClassCastAndCreditDao eventClassCastAndCreditDao;

    @Override
    public List<EventClassCastAndCredit> getEventClassCastAndCredites() {
        return eventClassCastAndCreditDao.getEventClassCastAndCredites();
    }

    @Override
    public EventClassCastAndCredit getEventClassCastAndCredit(Long id) {
        return eventClassCastAndCreditDao.getEventClassCastAndCredit(id);
    }

}
