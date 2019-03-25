package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.AdminPassesPagination;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.AdminPasses;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AdminPassesService {

    List<AdminPasses> getAdminPasses();

    AdminPassesPagination getAdminPassesPagination(int pageNumber, int maxRes);

    AdminPasses getAdminPasse(Long id);

    ResponseBodyEntity addPass(AdminPasses adminPass, MultipartFile image1) throws IOException;

    ResponseBodyEntity editPass(AdminPasses adminPass, MultipartFile image1) throws IOException;

}
