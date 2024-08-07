package com.quarkstream.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "d_image_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;

    private String imageOriginalName;

    private String imageType;

    private String imageUrl;

    private LocalDateTime uploadedTs;

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
