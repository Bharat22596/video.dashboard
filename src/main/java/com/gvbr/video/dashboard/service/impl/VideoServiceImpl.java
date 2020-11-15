package com.gvbr.video.dashboard.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gvbr.video.dashboard.models.Video;
import com.gvbr.video.dashboard.models.VideoResponse;
import com.gvbr.video.dashboard.repository.UserRepository;
import com.gvbr.video.dashboard.repository.VideoRepository;
import com.gvbr.video.dashboard.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	private final Path root = Paths.get("\\uploads").toAbsolutePath().normalize();

	@Autowired
	VideoRepository videoRepo;

	@Autowired
	UserRepository userRepo;

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public Video save(MultipartFile file, Long userId) {

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!userRepo.existsById(userId)) {
			throw new RuntimeException("Bad Request. User doesn't exist");
		} 
			else if (!"mp4".equalsIgnoreCase(extension)) {
			throw new RuntimeException("Bad Request. Wrong file format. Only MP4 files accepted.");
		}

		try {
			Video video = new Video();
			video.setExtension(extension);
			video.setUserId(userId);
			video.setSize(file.getSize());
			video.setDescription(file.getOriginalFilename());
			videoRepo.save(video);
			Files.copy(file.getInputStream(), this.root.resolve(video.getId() + "." + extension));
			return video;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public VideoResponse load(String videoId, Long userId) {
		VideoResponse videoResponse = new VideoResponse();
		Video video = videoRepo.getOne(videoId);

		if (!userRepo.existsById(userId)) {
			throw new RuntimeException("Bad Request. User doesn't exist");
		} else if (video == null || Long.valueOf(userId) != Long.valueOf(video.getUserId())) {
			throw new RuntimeException("Bad Request. Video doesn't exist");
		}

		videoResponse.setVideoDetails(videoRepo.getOne(videoId));
		try {
			Path file = root.resolve(videoId + ".mp4");
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				videoResponse.setVideoBytes(Files.readAllBytes(resource.getFile().toPath()));
				return videoResponse;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (Exception e) {
			throw new RuntimeException("Error fetching the file: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public List<VideoResponse> loadAll(Long userId) {

		if (!userRepo.existsById(userId)) {
			throw new RuntimeException("Bad Request. User doesn't exist");
		}

		List<Video> userVideos = videoRepo.findAllByUserId(userId);
		List<VideoResponse> videos = new ArrayList<>();

		if (userVideos != null && userVideos.size() > 0) {
			userVideos.parallelStream().forEach(v -> {
				VideoResponse newVid = new VideoResponse();
				newVid.setVideoDetails(v);
				Path file = root.resolve(v.getId() + ".mp4");
				try {
					Resource resource = new UrlResource(file.toUri());
					if (resource.exists() || resource.isReadable()) {
						newVid.setVideoBytes(Files.readAllBytes(resource.getFile().toPath()));
						videos.add(newVid);
					}
				} catch (IOException e) {
					throw new RuntimeException("Could not load the files!");
				}
			});
		}

		return videos;
	}

	@Override
	public Video update(Video video, String videoId) {
		Video existingVideo = videoRepo.getOne(videoId);

		if (existingVideo == null) {
			throw new RuntimeException("Bad Request. User doesn't exist");
		}
		if (StringUtils.isNotEmpty(video.getDescription()))
			existingVideo.setDescription(video.getDescription());
		if (StringUtils.isNotEmpty(video.getTitle()))
			existingVideo.setTitle(video.getTitle());
		if (video.getSize() > 0)
			existingVideo.setSize(video.getSize());
		
		existingVideo = videoRepo.save(existingVideo);

		return existingVideo;
	}

}
