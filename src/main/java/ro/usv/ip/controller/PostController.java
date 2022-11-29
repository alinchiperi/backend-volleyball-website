package ro.usv.ip.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = {""})
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value="/create", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public void createPost(@RequestPart("post") PostDto postDto, @RequestParam("images") MultipartFile[] files) {
       postService.create(postDto, files);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @GetMapping("posts/{tagName}")
    public List<PostDto> findPostByTagName(@PathVariable("tagName") String tagName) {
        return postService.findPostByTagName(tagName);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @PutMapping("/update/{id}")
    public PostDto update(@PathVariable Long id, @RequestBody PostDto postDto) {
        return postService.update(id, postDto);
    }

    @GetMapping("/posts")
    public List<PostDto> getPostsOderByDate() {
        return postService.getPostsOrderByDate();
    }


}
