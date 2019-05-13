package com.citerneApp.project.dao;

import com.citerneApp.project.model.AuditTrail;
import org.springframework.stereotype.Repository;

@Repository("auditTrailDao")
public class AuditTrailDaoImpl extends AbstractDao<Long, AuditTrail> implements AuditTrailDao {

    @Override
    public void addAuditTrail(AuditTrail auditTrail) {
        persist(auditTrail);
    }

}
