package com.citerneApp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tbl_event_class_media")
@XmlRootElement
@JsonInclude(JsonInclude.Include.ALWAYS)
public class EventClassMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GenericGenerator(name = "SEQ_GEN", strategy = "com.citerneApp.project.model.SequenceIdGenerator")
    @GeneratedValue(generator = "SEQ_GEN")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Basic(optional = false)
    @Column(name = "PATH")
    private String path;

    @JsonIgnore
    @JoinColumn(name = "EVENT_CLASS", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EventClass eventClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public EventClass getEventClass() {
        return eventClass;
    }

    public void setEventClass(EventClass eventClass) {
        this.eventClass = eventClass;
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
        if (!(object instanceof EventClassMedia)) {
            return false;
        }
        EventClassMedia other = (EventClassMedia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
