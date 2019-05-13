/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.EventClassCategory;
import com.citerneApp.project.service.EventClassCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventClassCategoryConverter implements Converter<Object, EventClassCategory> {

    @Autowired
    EventClassCategoryService eventClassCategoryService;

    /**
     * Gets Roles by Id
     *
     * @param element
     * @return
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public EventClassCategory convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            EventClassCategory eventClassCategory = eventClassCategoryService.getEventClassCategory(id);
            return eventClassCategory;
        }
        return (EventClassCategory) element;
    }
}
