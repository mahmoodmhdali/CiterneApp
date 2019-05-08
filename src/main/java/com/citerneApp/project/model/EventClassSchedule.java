package com.citerneApp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TBL_EVENT_CLASS_SCHEDULE")
@XmlRootElement
@JsonInclude(JsonInclude.Include.ALWAYS)
public class EventClassSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GenericGenerator(name = "SEQ_GEN", strategy = "com.citerneApp.project.model.SequenceIdGenerator")
    @GeneratedValue(generator = "SEQ_GEN")
    private Long id;

    @Basic(optional = false)
    @Column(name = "CLASS_DAY_INDEX")
    private Integer classDayIndex;

    @Basic(optional = false)
    @Column(name = "TIME")
    private Date time;

    @JsonIgnore
    @JoinColumn(name = "EVENT_CLASS", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.DETACH)
    private EventClass eventClass;

    @Basic(optional = false)
    @Column(name = "SHOW_DATETIME")
    private Date showDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EventClass getEventClass() {
        return eventClass;
    }

    public void setEventClass(EventClass eventClass) {
        this.eventClass = eventClass;
    }

    public Date getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(Date showDateTime) {
        this.showDateTime = showDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventClassSchedule)) {
            return false;
        }
        EventClassSchedule other = (EventClassSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
