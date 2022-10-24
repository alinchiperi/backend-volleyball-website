package ro.usv.ip.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.usv.ip.dto.NewPostDto;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = {""})
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public PostDto createPost(@RequestBody NewPostDto postDto) {
        return postService.create(postDto);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }
}
