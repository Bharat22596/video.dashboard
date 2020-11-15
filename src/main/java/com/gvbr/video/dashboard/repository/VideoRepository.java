package com.gvbr.video.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gvbr.video.dashboard.models.Video;
@Repository
public interface VideoRepository extends JpaRepository<Video, String> {
	
	public List<Video> findAllByUserId(Long userId);

}
