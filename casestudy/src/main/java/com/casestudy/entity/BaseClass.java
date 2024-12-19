package com.casestudy.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseClass {

	    @Column(name = "created_at", nullable = false, updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;
	    
	    @PrePersist
	    public void prePersist() {
	        this.createdAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    public void preUpdate() {
	        this.updatedAt = LocalDateTime.now();
	    }

}
