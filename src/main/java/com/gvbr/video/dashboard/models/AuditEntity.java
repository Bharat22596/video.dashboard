package com.gvbr.video.dashboard.models;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.util.StringUtils;

import com.gvbr.video.dashboard.constants.Constants;

import lombok.Data;

@MappedSuperclass
@Data
public class AuditEntity {
//	CR_DATE DATE,
//	  UP_DATE DATE,
//	  CR_BY VARCHAR(30) NOT NULL,
//	  UP_BY VARCHAR(30),
	
	@Column(name = "CR_BY")
	private String createdBy;
	
	@Column(name = "CR_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "UP_BY")
	private String updatedBy;
	
	@Column(name = "UP_DATE")
	private LocalDateTime updatedDate;
	
	@PrePersist
	public void preCreate() {
		if(!StringUtils.hasText(createdBy)) {
			this.createdBy= Constants.ADMIN;
		}
		this.createdDate = LocalDateTime.now(Clock.systemUTC());
	}
	
	@PreUpdate
	public void preUpdate() {
		if(!StringUtils.hasText(updatedBy)) {
			this.updatedBy= Constants.ADMIN;
		}
		this.updatedDate = LocalDateTime.now(Clock.systemUTC());
	}

}
