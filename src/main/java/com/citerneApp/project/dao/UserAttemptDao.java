
package com.citerneApp.project.dao;

import com.citerneApp.project.model.UserAttempt;
import java.util.List;

public interface UserAttemptDao {

    void initAttempt(UserAttempt userAttempt);

    UserAttempt findById(Long userId);

    List<UserAttempt> getByAccountLocked();
}
