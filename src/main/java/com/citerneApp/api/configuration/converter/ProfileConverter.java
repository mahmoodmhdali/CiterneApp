/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.Profile;
import com.citerneApp.project.model.Role;
import com.citerneApp.project.service.ProfileService;
import com.citerneApp.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter implements Converter<Object, Profile> {

    @Autowired
    ProfileService profileService;

    /**
     * Gets Roles by Id
     *
     * @param element
     * @return
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public Profile convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            Profile profile = profileService.getProfile(id);
            return profile;
        }
        return (Profile) element;
    }
}
