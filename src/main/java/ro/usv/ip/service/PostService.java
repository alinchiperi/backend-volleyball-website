package ro.usv.ip.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.NewPostDto;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.exceptions.PostNotFoundException;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.PostRepository;

import java.time.LocalDateTime;
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


    public PostDto create(PostDto postData) {
        List<Tag> tags = tagService.tagsFrom(postData.getTags());
        Post post = postFor(postData.getTitle());
        log.info(String.valueOf(post.getCreatedOn()));

        post.setContent(postData.getContent());

        log.info(post.getContent());

        Post result = postRepository.save(post);

        return PostDto.from(result);
    }

    public Page<PostDto> findAll(Pageable pageable) {
        Page<Post> results = postRepository.findAll(pageable);
        return results.map(PostDto::from);
    }

    static Post postFor(String title) {
        Post post = new Post();
        post.setTitle(title);
        post.setCreatedOn(LocalDateTime.now());
        return post;
    }
    public PostDto update(Long postId, NewPostDto postData) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        List<Tag> tags = tagService.tagsFrom(postData.getTags());

        updateTitleAndTags(post, postData.getTitle(), tags);

        Post result = postRepository.saveAndFlush(post);
        return PostDto.from(result);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private void updateTitleAndTags(Post post, String title, List<Tag> tags) {
        post.setTitle(title);
        post.getTags().clear();
        post.getTags().addAll(tags);
    }

}
