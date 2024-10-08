package com.springboot3demo.springtest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final VideoService videoService;
    private final VideoJPAService videoJPAService;

    public HomeController(VideoService videoService, VideoJPAService videoJPAService) {
        this.videoService = videoService;
        this.videoJPAService = videoJPAService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("videos", videoJPAService.getVideos());
        model.addAttribute("authentication", authentication);
        return "index";
    }

    @GetMapping("/react")
    public String react() {
        return "react";
    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute NewVideo newVideo, Authentication authentication) {
        videoJPAService.create(newVideo, authentication.getName());
        return "redirect:/";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(
            @ModelAttribute VideoSearch search,
            Model model
    ) {
        List<VideoEntity> searchResults = videoJPAService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        List<VideoEntity> searchResults = videoJPAService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/delete/videos/{videoId}")
    public String deleteVideo(@PathVariable Long videoId) {
        videoJPAService.delete(videoId);
        return "redirect:/";
    }
}
