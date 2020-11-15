package com.gvbr.video.dashboard.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "VIDEOS")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class Video extends AuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2525494627067464493L;
//	 VIDEO_ID INT VARCHAR(36)  PRIMARY KEY,
//	  USER_ID INT NOT NULL,
//	  TITLE VARCHAR(250) NOT NULL,
//	  DESC VARCHAR(250),
//	  EXTENSION VARCHAR(5) NOT NULL,
//	  TIME NUMBER NOT NULL,

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "VIDEO_ID")
	private String id;
	
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESC")
	private String description;

	@Column(name = "SIZE")
	private Long size;
	
	@Column(name = "EXTENSION")
	private String extension;

}
