package com.citerneApp.project.helpermodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class HomePageEvents {

    private Long id;

    private String title;

    private String categoryName;

    private String ticketingURL;

    private Integer duration;

    private String profileName;

    private String typeName;

    private String mainImage;

    private String countryName;

    @JsonIgnore
    private Integer ind;

    @JsonIgnore
    private Integer classDayIndex;

    @JsonIgnore
    private Date time;

    @JsonIgnore
    private Date showDateTime;

    private List<HomePageEventSchedule> eventSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getClassDayIndex() {
        return classDayIndex;
    }

    public void setClassDayIndex(Integer classDayIndex) {
        this.classDayIndex = classDayIndex;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(Date showDateTime) {
        this.showDateTime = showDateTime;
    }

    public List<HomePageEventSchedule> getEventSchedule() {
        return eventSchedule;
    }

    public void setEventSchedule(List<HomePageEventSchedule> eventSchedule) {
        this.eventSchedule = eventSchedule;
    }

    public String getTicketingURL() {
        return ticketingURL;
    }

    public void setTicketingURL(String ticketingURL) {
        this.ticketingURL = ticketingURL;
    }

    public Integer getInd() {
        return ind;
    }

    public void setInd(Integer ind) {
        this.ind = ind;
    }

}
