package com.citerneApp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TBL_PROFILE")
@XmlRootElement
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GenericGenerator(name = "SEQ_GEN", strategy = "com.citerneApp.project.model.SequenceIdGenerator")
    @GeneratedValue(generator = "SEQ_GEN")
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME")
    @Size(max = 20, message = "validation.Profile.nameRange")
    @NotBlank(message = "validation.Profile.nameRequired")
    private String name;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @JsonIgnore
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @JoinTable(name = "TBL_EVENT_CLASS_PROFILES", joinColumns = {
        @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EVENT_CLASS_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<EventClass> eventClassCollection;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Collection<ProfileMedia> profileMedias;

    @Basic(optional = false)
    @Column(name = "ABOUT")
    private String about;

    @Basic(optional = false)
    @Column(name = "IMAGE_PATH")
    private String imagePath;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

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

    public Collection<EventClass> getEventClassCollection() {
        return eventClassCollection;
    }

    public void setEventClassCollection(Collection<EventClass> eventClassCollection) {
        this.eventClassCollection = eventClassCollection;
    }

    public Collection<ProfileMedia> getProfileMedias() {
        return profileMedias;
    }

    public void setProfileMedias(Collection<ProfileMedia> profileMedias) {
        this.profileMedias = profileMedias;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
