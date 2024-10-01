package fr.formation.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.exception.UserNotFoundException;
import fr.formation.exception.VideoNotFoundException;
import fr.formation.model.User;
import fr.formation.model.Video;
import fr.formation.repo.UserRepository;
import fr.formation.repo.VideoRepository;
import fr.formation.request.CreateOrUpdateVideoRequest;
import fr.formation.response.VideoByIdResponse;
import fr.formation.response.VideoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
@Log4j2
public class VideoApiController {
    private final VideoRepository repository;
    private final UserRepository userRepository;

    @GetMapping
    public List<VideoResponse> findAll() {
        log.debug("Finding all videos ...");

        return this.repository.findAll()
            .stream()
            .map(this::convert)
            .toList()
        ;
    }

    @GetMapping("/{id}")
    public VideoByIdResponse findById(@PathVariable String id) {
        log.debug("Finding video {} ...", id);
        
        Video video = this.repository.findById(id).orElseThrow(VideoNotFoundException::new);
        VideoByIdResponse resp = VideoByIdResponse.builder().build();
        
        BeanUtils.copyProperties(video, resp);

        return resp;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public String create(@RequestBody CreateOrUpdateVideoRequest request, Authentication authentication) {
        log.debug("Creating video ...");

        Video video = new Video();
        User owner = this.userRepository.findById(authentication.getPrincipal().toString()).orElseThrow(UserNotFoundException::new);

        BeanUtils.copyProperties(request, video);

        video.setDate(LocalDateTime.now());
        video.setOwner(owner);

        this.repository.save(video);

        log.debug("Video {} created!", video.getId());
        
        return video.getId();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String update(@PathVariable String id, @RequestBody CreateOrUpdateVideoRequest request) {
        log.debug("Updating video {} ...", id);
        
        Video video = this.repository.findById(id).orElseThrow(VideoNotFoundException::new);
        
        BeanUtils.copyProperties(request, video);
        
        this.repository.save(video);
        
        log.debug("Video {} updated!", id);
        
        return video.getId();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteById(@PathVariable String id) {
        log.debug("Deleting video {} ...", id);
        
        this.repository.deleteById(id);

        log.debug("Video {} deleted!", id);
    }

    private VideoResponse convert(Video video) {
        VideoResponse resp = VideoResponse.builder().build();

        BeanUtils.copyProperties(video, resp);

        return resp;
    }
}
