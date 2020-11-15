package com.gvbr.video.dashboard;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gvbr.video.dashboard.service.VideoService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application implements CommandLineRunner{
	
	@Resource
	VideoService videoService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	  public void run(String... arg) throws Exception {
		videoService.deleteAll();
		videoService.init();
	  }

}
