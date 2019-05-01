package com.citerneApp.project.service;

import com.citerneApp.project.helpermodel.ProfilesPagination;
import com.citerneApp.project.helpermodel.ResponseBodyEntity;
import com.citerneApp.project.model.Profile;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

    List<Profile> getProfilees();

    ProfilesPagination getProfilesPagination(int pageNumber, int maxRes);

    Profile getProfile(Long id);

    Profile getProfile(String name);

    ResponseBodyEntity addProfile(Profile profile, MultipartFile image1) throws IOException;

    ResponseBodyEntity editProfile(Profile profile, MultipartFile image1) throws IOException;

}
