package com.springboot3demo.springtest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private final VideoService videoService;
    private final VideoJPAService videoJPAService;

    public ApiController(VideoService videoService, VideoJPAService videoJPAService) {
        this.videoService = videoService;
        this.videoJPAService = videoJPAService;
    }

    @GetMapping("/api/videos")
    public List<VideoEntity> all() {
        return videoJPAService.getVideos();
    }

    @PostMapping("/api/videos")
    public VideoEntity create(@RequestBody NewVideo newVideo, Authentication authentication) {
        return videoJPAService.create(newVideo, authentication.getName());
    }
}
