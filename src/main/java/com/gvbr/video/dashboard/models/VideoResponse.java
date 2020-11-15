package com.gvbr.video.dashboard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponse {
	
	private Video videoDetails;
	
	private byte[] videoBytes;

}
