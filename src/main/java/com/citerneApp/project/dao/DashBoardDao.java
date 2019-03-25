package com.citerneApp.project.dao;

import com.citerneApp.project.model.DashBoard;
import java.sql.SQLException;
import java.util.List;

public interface DashBoardDao {

    List<DashBoard> getCounters() throws SQLException, Exception;

    List<DashBoard> getCountersByType(Long id);

    DashBoard getCountersByKey(String key);

}
