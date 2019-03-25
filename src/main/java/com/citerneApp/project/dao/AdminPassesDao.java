package com.citerneApp.project.dao;

import com.citerneApp.project.helpermodel.AdminPassesPagination;
import com.citerneApp.project.model.AdminPasses;
import java.util.List;

public interface AdminPassesDao {

    List<AdminPasses> getAdminPasses();

    AdminPassesPagination getAdminPassesPagination(int pageNumber, int maxRes);

    AdminPasses getAdminPasse(Long id);

    void addAdminPasse(AdminPasses adminPasse);
}
