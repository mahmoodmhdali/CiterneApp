package com.citerneApp.project.dao;

import com.citerneApp.project.model.Blacklist;
import java.util.List;

public interface BlacklistDao {

    List<Blacklist> getBlacklists(String source);

    Blacklist getBlacklist(Long id);

    Blacklist filterByMobileNumber(String mobileNumber);

    void addBlacklist(Blacklist user);

    void deleteBlacklist(Blacklist user);
}
