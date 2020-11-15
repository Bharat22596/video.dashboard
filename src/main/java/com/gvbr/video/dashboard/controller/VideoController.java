package com.gvbr.video.dashboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gvbr.video.dashboard.constants.APIReference;
import com.gvbr.video.dashboard.constants.Constants;
import com.gvbr.video.dashboard.models.Metadata;
import com.gvbr.video.dashboard.models.Response;
import com.gvbr.video.dashboard.models.Video;
import com.gvbr.video.dashboard.models.VideoResponse;
import com.gvbr.video.dashboard.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(value = Constants.VIDEO_CONTROLLER)
public class VideoController {

	@Autowired
	VideoService videoService;

	@PostMapping(value = APIReference.UPLOAD_VIDEO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Upload new Video")
	public @ResponseBody Response<Video> uploadVideo(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") Long userId) {
		Video video = videoService.save(file, userId);
		Response<Video> response = new Response<Video>(new Metadata(true, "Uploaded the Video successfully "), video);
		return response;
	}
	
	@PutMapping(value = APIReference.UPDATE_VIDEO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Update new Video")
	public @ResponseBody Response<Video> updateVideo(@RequestBody Video video,
			@RequestParam("videoId") String videoId) {
		video = videoService.update(video, videoId);
		Response<Video> response = new Response<Video>(new Metadata(true, "Updated the Video successfully "), video);
		return response;
	}

	@GetMapping(value = APIReference.GET_VIDEOS)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Fetch Videos of User")
	public Response<List<VideoResponse>> getVideos(@RequestParam("userId") Long userId) {
		List<VideoResponse> videos = videoService.loadAll(userId);
		return new Response<List<VideoResponse>>(new Metadata(true, "Fetched Videos of user"), videos);
	}

	@GetMapping(value = APIReference.GET_VIDEO)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Fetch Video")
	public @ResponseBody Response<VideoResponse> getVideo(@RequestParam("videoId") String videoId,
			@RequestParam("userId") Long userId, HttpServletRequest request) {
		VideoResponse video = videoService.load(videoId, userId);

		return new Response<VideoResponse>(new Metadata(true, "Fetched Video"), video);
	}
}
