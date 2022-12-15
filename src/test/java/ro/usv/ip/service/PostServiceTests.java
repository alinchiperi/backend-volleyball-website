package ro.usv.ip.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import ro.usv.ip.dto.PostDto;
import ro.usv.ip.dto.TagDto;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.Tag;
import ro.usv.ip.repository.PostRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {
    @Mock
    private PostRepository postRepository;

    @Mock
    private TagService tagService;

    @InjectMocks
    private PostService postService;

    MultipartFile[] files;

    @Test
    public void
    returnPost_WhenCreatePost_ForProvidedData() {
        List<TagDto> tagDtos = List.of(
                TagDto.builder().name("csm").build(),
                TagDto.builder().name("slice testing").build());
        List<Tag> tags = List.of(
                new Tag(1L, "spring boot"),
                new Tag(2L, "slice testing"));
        given(tagService.tagsFrom(tagDtos)).willReturn(tags);

        String postTitle = "Post title ";
        Post post = new Post();
        post.setId(1L);
        post.setTitle(postTitle);
        post.setCreatedBy(("Alin"));
        post.getTags().addAll(tags);
        postRepository.save(post);
//        given(postRepository.save(any(Post.class))).willReturn(post);

        PostDto newPost = PostDto.builder()
                .title(postTitle)
                .tags(tagDtos)
                .build();

        PostDto result = postService.create(newPost, files);

        assertThat(result).isNotNull();
    }
}
