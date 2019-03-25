/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.UserProfile;
import com.citerneApp.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<Object, UserProfile> {

    @Autowired
    UserService userService;

    @Override
    public UserProfile convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            UserProfile userProfile = userService.toUser(id);
            return userProfile;
        }
        return (UserProfile) element;
    }
}
