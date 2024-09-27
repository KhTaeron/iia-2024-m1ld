package fr.formation.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.CreateCommentRequest;
import fr.formation.response.CommentResponse;

@RestController
@RequestMapping("/api/comment")
public class CommentApiController {
    @GetMapping
    public List<CommentResponse> findAllByVideoId(@PathVariable String videoId) {
        return new ArrayList<>();
    }

    @PostMapping
    public String create(@RequestBody CreateCommentRequest request) {
        return "";
    }
}
