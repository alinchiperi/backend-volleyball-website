package ro.usv.ip.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.usv.ip.dto.NewPostDto;
import ro.usv.ip.dto.PostCommentDto;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.exceptions.PostNotFoundException;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.PostComment;
import ro.usv.ip.model.PostDetails;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.PostRepository;

import java.util.List;


@Service

public class PostService {
    private final PostRepository postRepository;
    private final TagService tagService;

    public PostService(PostRepository posts, TagService tagService) {
        this.postRepository = posts;
        this.tagService = tagService;
    }

    public PostDto create(NewPostDto postData) {
        List<Tag> tags = tagService.tagsFrom(postData.getTags());

        Post post = postFor(postData.getTitle(), tags);

        Post result = postRepository.save(post);
        return PostDto.from(result);
    }

    public Page<PostDto> findAll(Pageable pageable) {
        Page<Post> results = postRepository.findAll(pageable);
        return results.map(PostDto::from);
    }
    static Post postFor(String title, List<Tag> tags) {
        //ToDo complex business logic
        Post post = new Post();
        post.setTitle(title);
        post.getTags().clear();
        post.getTags().addAll(tags);
        post.setDetails(createDetails());
        return post;
    }

    static private PostDetails createDetails() {
        //TODO: Add logic here or create service for post details
        return new PostDetails("Alin");
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

    public void addComment(Long postId, PostCommentDto commentDto) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));

        existingPost.addComment(commentFrom(commentDto));
    }
    private PostComment commentFrom(PostCommentDto commentDto) {
        //ToDo:Add logic
        return new PostComment(commentDto.getReview(), "Chiperi");
    }





}
