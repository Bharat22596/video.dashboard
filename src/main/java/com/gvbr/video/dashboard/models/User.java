package com.gvbr.video.dashboard.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USER")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class User extends AuditEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1227517281234105161L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	
	@NotNull
	@Column(name = "USER_NAME")
	private String userName;
	
	@NotNull
	@Column(name = "EMAIL")
	private String emailId;
	
	@NotNull
//	@JsonIgnore
	@Column(name = "PASSWORD")
	private String password;
}
