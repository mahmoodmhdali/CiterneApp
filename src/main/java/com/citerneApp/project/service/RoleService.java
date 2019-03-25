package com.citerneApp.project.service;

import com.citerneApp.project.model.Role;
import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRole(Long id);

    Role getRole(String name);
}
