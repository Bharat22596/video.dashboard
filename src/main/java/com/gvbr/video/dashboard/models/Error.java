package com.gvbr.video.dashboard.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7789293203225405818L;
	
	private String code;
	
	private String message;

}
