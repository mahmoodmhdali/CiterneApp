package com.citerneApp.project.model;

import com.citerneApp.project.model.validation.ValidName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "TBL_EVENT_CLASS")
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
    @Column(name = "TICKETING_URL")
    @Size(max = 200, message = "validation.userProfile.ticketingURLRange")
    private String ticketingURL;

    @JoinColumn(name = "CATEGORY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private EventClassCategory eventClassCategory;

    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private EventClassType eventClassType;

    @JoinColumn(name = "COUNTRY", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private EventClassCountry eventClassCountry;

    @JoinTable(name = "TBL_EVENT_CLASS_PROFILES", inverseJoinColumns = {
        @JoinColumn(name = "PROFILE_ID", referencedColumnName = "ID")}, joinColumns = {
        @JoinColumn(name = "EVENT_CLASS_ID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Profile> profileCollection;

    @Basic(optional = false)
    @Column(name = "ABOUT")
    @Size(min = 20, message = "validation.userProfile.aboutRange")
    @NotBlank(message = "validation.userProfile.aboutRequired")
    private String about;

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

    @JsonIgnore
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISHED")
    private boolean published = true;

    @Basic(optional = false)
    @Column(name = "DURATION")
    private Integer duration;

    @OneToMany(mappedBy = "eventClass", cascade = CascadeType.ALL)
    private Collection<EventClassImage> eventClassImages;

    @OneToMany(mappedBy = "eventClass", cascade = CascadeType.ALL)
    private List<EventClassCastAndCredit> eventClassCastAndCredits;

    @OneToMany(mappedBy = "eventClass", cascade = CascadeType.ALL)
    private List<EventClassMedia> eventClassMedias;

    @OneToMany(mappedBy = "eventClass", cascade = CascadeType.ALL)
    private List<EventClassSchedule> eventClassSchedules;

    @OneToMany(mappedBy = "eventClass", cascade = CascadeType.ALL)
    private Collection<Favorite> favorites;

    @Transient
    private String imageName1;

    @Transient
    private String imageName2;

    @Transient
    private String imageName3;

    @Transient
    private String imageName4;

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

    public EventClassCategory getEventClassCategory() {
        return eventClassCategory;
    }

    public void setEventClassCategory(EventClassCategory eventClassCategory) {
        this.eventClassCategory = eventClassCategory;
    }

    public EventClassType getEventClassType() {
        return eventClassType;
    }

    public void setEventClassType(EventClassType eventClassType) {
        this.eventClassType = eventClassType;
    }

    public EventClassCountry getEventClassCountry() {
        return eventClassCountry;
    }

    public void setEventClassCountry(EventClassCountry eventClassCountry) {
        this.eventClassCountry = eventClassCountry;
    }

    public Collection<Profile> getProfileCollection() {
        return profileCollection;
    }

    public void setProfileCollection(Collection<Profile> profileCollection) {
        this.profileCollection = profileCollection;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Collection<EventClassImage> getEventClassImages() {
        return eventClassImages;
    }

    public void setEventClassImages(Collection<EventClassImage> eventClassImages) {
        this.eventClassImages = eventClassImages;
    }

    public List<EventClassCastAndCredit> getEventClassCastAndCredits() {
        return eventClassCastAndCredits;
    }

    public void setEventClassCastAndCredits(List<EventClassCastAndCredit> eventClassCastAndCredits) {
        this.eventClassCastAndCredits = eventClassCastAndCredits;
    }

    public List<EventClassMedia> getEventClassMedias() {
        return eventClassMedias;
    }

    public void setEventClassMedias(List<EventClassMedia> eventClassMedias) {
        this.eventClassMedias = eventClassMedias;
    }

    public List<EventClassSchedule> getEventClassSchedules() {
        return eventClassSchedules;
    }

    public void setEventClassSchedules(List<EventClassSchedule> eventClassSchedules) {
        this.eventClassSchedules = eventClassSchedules;
    }

    public Collection<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection<Favorite> favorites) {
        this.favorites = favorites;
    }

    public String getTicketingURL() {
        return ticketingURL;
    }

    public void setTicketingURL(String ticketingURL) {
        this.ticketingURL = ticketingURL;
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
