package com.gvbr.video.dashboard.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gvbr.video.dashboard.models.Video;
import com.gvbr.video.dashboard.models.VideoResponse;

public interface VideoService {
	
	  public void init();

	  public Video save(MultipartFile file, Long userId);

	  public VideoResponse load(String videoId,Long userId);

	  public void deleteAll();

	  public List<VideoResponse> loadAll(Long userId);

	public Video update(Video video, String userId);

}
