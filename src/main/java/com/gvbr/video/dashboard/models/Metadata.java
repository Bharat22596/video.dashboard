package com.gvbr.video.dashboard.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {
	
	private boolean success;
	
	private String responseId;
	
	private String description;
	
	@JsonInclude
	private Error error;

	public Metadata(boolean success, String description) {
		super();
		this.success = success;
		this.responseId = UUID.randomUUID().toString();
		this.description = description;
	}

	public Metadata(boolean success, String description, Error error) {
		super();
		this.success = success;
		this.responseId = UUID.randomUUID().toString();
		this.description = description;
		this.error = error;
	}
	
	
	
	

}
