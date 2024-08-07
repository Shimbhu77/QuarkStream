package com.quarkstream.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "d_channel_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer channelId;

    private String channelUsername;

    private String channelName;

    private String channelDescription;

    private LocalDate channelCreated;

    private Long totalSubscribers;

    private Long totalUploadedVideos;

    private Long totalViews;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private Image bannerImage;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = false)
    private Image profileImage;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();

    private LocalDateTime effTs;

    private LocalDateTime endTs;

    @PrePersist
    public void prePersist() {
        this.effTs = LocalDateTime.now();
        this.endTs = LocalDateTime.parse("9999-12-31T23:59:59", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @PreUpdate
    public void preUpdate() {
        // Update effTs to current time when the record is updated, if needed.
        this.effTs = LocalDateTime.now();
    }

}
