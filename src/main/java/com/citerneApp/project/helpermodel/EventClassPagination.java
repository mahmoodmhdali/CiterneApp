/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citerneApp.project.helpermodel;

import com.citerneApp.project.model.EventClass;
import java.util.List;

/**
 *
 * @author mahmoudmhdali
 */
public class EventClassPagination {

    private int maxPages;
    private int currentPage;
    private int totalResults;
    List<EventClass> eventClasses;

    public EventClassPagination(int maxPages, int currentPage, int totalResults, List<EventClass> eventClasses) {
        this.maxPages = maxPages;
        this.currentPage = currentPage;
        this.totalResults = totalResults;
        this.eventClasses = eventClasses;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(int maxPages) {
        this.maxPages = maxPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<EventClass> getEventClasses() {
        return eventClasses;
    }

    public void setEventClasses(List<EventClass> eventClasses) {
        this.eventClasses = eventClasses;
    }

}
