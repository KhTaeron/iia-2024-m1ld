package fr.formation.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.CreateOrUpdateVideoRequest;
import fr.formation.response.VideoResponse;

@RestController
@RequestMapping("/api/video")
public class VideoApiController {
    @GetMapping
    public List<VideoResponse> findAll() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public VideoResponse findById(@PathVariable String id) {
        return VideoResponse.builder()
            .date(LocalDateTime.of(2024, 11, 2, 11, 54, 25))
            .build()
        ;
    }

    @PostMapping
    public String create(@RequestBody CreateOrUpdateVideoRequest request) {
        return "";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable String id, @RequestBody CreateOrUpdateVideoRequest request) {
        return "";
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {

    }
}
