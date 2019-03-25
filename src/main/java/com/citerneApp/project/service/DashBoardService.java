package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.DashBoard;
import java.sql.SQLException;
import java.util.List;

public interface DashBoardService {

    /**
     *
     * @return all counters
     */
    List<DashBoard> getCounters() throws SQLException, Exception;

    /**
     *
     * @return counters by type
     */
    List<DashBoard> getCountersByType(Long id);

    /**
     *
     * @param key the key of the counter
     * @return {@link com.citerneApp.MCAAPI.model.DashBoard}
     */
    ResponseBodyEntity getCountersBykey(String key);

}
