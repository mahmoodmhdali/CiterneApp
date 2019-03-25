package com.citerneApp.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.citerneApp.project.model.validation.ValidName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "TBL_EVENT_CLASSE")
@XmlRootElement
@JsonInclude(JsonInclude.Include.ALWAYS)
public class EventClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GenericGenerator(name = "SEQ_GEN", strategy = "com.citerneApp.project.model.SequenceIdGenerator")
    @GeneratedValue(generator = "SEQ_GEN")
    private Long id;

    @Basic(optional = false)
    @Column(name = "TITLE")
    @Size(min = 5, max = 20, message = "validation.userProfile.titleRange")
    @NotBlank(message = "validation.userProfile.titleRequired")
    @ValidName
    private String title;

    @Basic(optional = false)
    @Column(name = "CATEGORY")
    @NotNull(message = "validation.userProfile.categoryRequired")
    private Long category;

    @Basic(optional = false)
    @Column(name = "TYPE")
    @NotNull(message = "validation.userProfile.typeRequired")
    private Long type;

    @Basic(optional = false)
    @Column(name = "AUTHOR")
    @NotNull(message = "validation.userProfile.authorRequired")
    private Long author;

    @Basic(optional = false)
    @Column(name = "ABOUT")
    @Size(min = 20, message = "validation.userProfile.aboutRange")
    @NotBlank(message = "validation.userProfile.aboutRequired")
    private String about;

    @CreationTimestamp
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISHED")
    private boolean published = true;

//    @JoinTable(name = "TBL_USER_PROFILE_GROUPS", inverseJoinColumns = {
//        @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID")}, joinColumns = {
//        @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "ID")})
//    @ManyToMany(fetch = FetchType.LAZY)
//    private Collection<Group> groupCollection;
//
//    @OneToMany(mappedBy = "notificationEvents", cascade = CascadeType.ALL)
//    private List<UserProfileNotificationEvent> userProfileNotificationEventCollection;
//
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userProfileId", cascade = CascadeType.ALL)
//    private UserAttempt userAttempt;
//
//    private transient Collection<GrantedAuthority> authorities;
//
//    @JoinColumn(name = "language_id", referencedColumnName = "ID")
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private Language language;
    public EventClass() {
    }

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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

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

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
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
        if (!(object instanceof EventClass)) {
            return false;
        }
        EventClass other = (EventClass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
