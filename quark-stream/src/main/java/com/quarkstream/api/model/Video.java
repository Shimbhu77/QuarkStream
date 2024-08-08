package com.quarkstream.api.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "d_video_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;

    private String videoOriginalName;

    private String videoTitle;

    private String videoDescription;

    private String videoType;

    private String videoUrl;

    private LocalDateTime uploadedTs;

    private LocalDateTime effTs;

    private LocalDateTime endTs;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Channel channel;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Image> thumbnails = new ArrayList<>();

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
