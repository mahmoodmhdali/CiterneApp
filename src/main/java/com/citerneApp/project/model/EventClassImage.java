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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "TBL_EVENT_CLASS_IMAGE")
@XmlRootElement
@JsonInclude(JsonInclude.Include.ALWAYS)
public class EventClassImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GenericGenerator(name = "SEQ_GEN", strategy = "com.citerneApp.project.model.SequenceIdGenerator")
    @GeneratedValue(generator = "SEQ_GEN")
    private Long id;

    @Basic(optional = false)
    @Column(name = "IMAGE_INDEX")
    private Integer imageIndex;

    @Basic(optional = false)
    @Column(name = "PATH")
    private String path;

    @Basic(optional = false)
    @Column(name = "NAME")
    private String fileName;

    @JsonIgnore
    @JoinColumn(name = "EVENT_CLASS", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EventClass eventClass;

    @Transient
    private String imageName1;

    @Transient
    private String imageName2;

    @Transient
    private String imageName3;

    @Transient
    private String imageName4;

    public Integer getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(Integer imageIndex) {
        this.imageIndex = imageIndex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventClass getEventClass() {
        return eventClass;
    }

    public void setEventClass(EventClass eventClass) {
        this.eventClass = eventClass;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageName1() {
        return imageName1;
    }

    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    public String getImageName2() {
        return imageName2;
    }

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    public String getImageName3() {
        return imageName3;
    }

    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }

    public String getImageName4() {
        return imageName4;
    }

    public void setImageName4(String imageName4) {
        this.imageName4 = imageName4;
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
        if (!(object instanceof EventClassImage)) {
            return false;
        }
        EventClassImage other = (EventClassImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
