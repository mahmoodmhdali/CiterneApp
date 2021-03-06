/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.api.configuration.converter;

import com.citerneApp.project.model.EventClass;
import com.citerneApp.project.service.EventClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventClassConverter implements Converter<Object, EventClass> {

    @Autowired
    EventClassService eventClassService;

    /**
     * Gets Roles by Id
     *
     * @param element
     * @return
     * @see
     * org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public EventClass convert(Object element) {
        if (element instanceof String) {
            Long id = Long.parseLong((String) element);
            EventClass eventClass = eventClassService.getEventClass(id);
            return eventClass;
        }
        return (EventClass) element;
    }
}
