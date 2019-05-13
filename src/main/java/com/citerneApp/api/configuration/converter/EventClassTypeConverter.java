/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.EventClassType;
import com.citerneApp.project.service.EventClassTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventClassTypeConverter implements Converter<Object, EventClassType> {

    @Autowired
    EventClassTypeService eventClassTypeService;

    /**
     * Gets Roles by Id
     *
     * @param element
     * @return
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public EventClassType convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            EventClassType eventClassType = eventClassTypeService.getEventClassType(id);
            return eventClassType;
        }
        return (EventClassType) element;
    }
}
