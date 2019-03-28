package com.citerneApp.project.helpermodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class HomePageEventSchedule {

    private Integer classDayIndex;

    private Date time;

    private Date showDateTime;

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

}
