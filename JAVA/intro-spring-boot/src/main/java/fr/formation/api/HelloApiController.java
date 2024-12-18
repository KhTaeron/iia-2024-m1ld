package fr.formation.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.HelloRequest;
import fr.formation.response.HelloResponse;

@RestController
@RequestMapping("/hello")
public class HelloApiController {
    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping
    // public HelloResponse hello(@RequestParam String username, @RequestParam String password) {
    public HelloResponse getHello(HelloRequest request) {
        return HelloResponse.builder()
            // .user(username)
            .user(request.getUsername())
            .build()
        ;
    }
    
    @GetMapping("/{id}")
    public HelloResponse getHelloPv(@PathVariable String id) {
        return HelloResponse.builder()
            // .user(username)
            .user(id)
            .build()
        ;
    }

    @PostMapping
    public HelloResponse postHello(@RequestBody HelloRequest request) {
        return HelloResponse.builder()
            .user(request.getUsername())
            .build()
        ;
    }
}
