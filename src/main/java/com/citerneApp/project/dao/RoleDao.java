
package com.citerneApp.project.dao;

import com.citerneApp.project.model.Role;
import java.util.List;

public interface RoleDao {

    List<Role> getRoles();

    Role getRole(Long id);
    
    Role getRole(String name);
    
}
