package com.gvbr.video.dashboard.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -972996619890272422L;
	
	private Metadata metadata;
	
	private T data;

	public Response(T data) {
		super();
		this.data = data;
	}
	
	

}
