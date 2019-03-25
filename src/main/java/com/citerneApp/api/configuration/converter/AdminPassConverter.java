/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.AdminPasses;
import com.citerneApp.project.service.AdminPassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AdminPassConverter implements Converter<Object, AdminPasses> {

    @Autowired
    AdminPassesService adminPassesService;

    @Override
    public AdminPasses convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            AdminPasses adminPasse = adminPassesService.getAdminPasse(id);
            return adminPasse;
        }
        return (AdminPasses) element;
    }
}
