package ro.usv.ip.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.exceptions.PostNotFoundException;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final TagService tagService;

    public PostService(PostRepository postRepository, TagService tagService) {
        this.postRepository = postRepository;
        this.tagService = tagService;
    }


    public PostDto create(PostDto postDto) {
        List<Tag> tags = tagService.tagsFrom(postDto.getTags());

        Post post = postFor(postDto.getTitle(), tags);

        post.setContent(postDto.getContent());
        post.setCreatedBy(postDto.getCreatedBy());
        post.setUnderTitle(postDto.getUnderTitle());

        Post result = postRepository.save(post);

        return PostDto.from(result);
    }


    static Post postFor(String title, List<Tag> tags) {
        Post post = new Post();
        post.setTitle(title);
        post.getTags().clear();
        post.getTags().addAll(tags);
        post.setCreatedOn(LocalDateTime.now());
        return post;
    }

    public List<PostDto> findPostByTagName(String tagName) {
        List<Post> posts = postRepository.findByTags_Name(tagName);

        return changePostToDto(posts);
    }

    public PostDto update(Long postId, PostDto postDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        List<Tag> tags = tagService.tagsFrom(postDto.getTags());

        post.setTitle(postDto.getTitle());
        post.getTags().clear();
        post.getTags().addAll(tags);

        post.setContent(postDto.getContent());
        post.setCreatedBy(postDto.getCreatedBy());
        post.setUnderTitle(postDto.getUnderTitle());

        Post result = postRepository.saveAndFlush(post);
        return PostDto.from(result);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }


    public PostDto findPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        return PostDto.from(post);
    }

    static List<PostDto> changePostToDto(List<Post> posts) {
        List<PostDto> postDtos = new ArrayList<>();
        for (Post p : posts) {
            postDtos.add(PostDto.from(p));
        }
        return postDtos;

    }

    public List<PostDto> getPostsOrderByDate() {

        List<Post> posts = postRepository.findAllOrderByCreatedOnAsc();

        return changePostToDto(posts);

    }

}
