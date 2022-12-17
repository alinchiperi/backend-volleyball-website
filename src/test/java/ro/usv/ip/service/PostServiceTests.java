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

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    LocalDateTime currentTime = LocalDateTime.now();

    @Test
    public void
    returnPost_WhenGiveIdValid() {
        Post post = getPost();

        given(postRepository.findById(any())).willReturn(Optional.of(post));

        PostDto result = postService.findPostById(post.getId());

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Post title");
        assertThat(result.getTags()).isEqualTo(List.of(
                TagDto.builder().id(1L).name("csm").build(),
                TagDto.builder().id(2L).name("Suceava").build()));
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getCreatedBy()).isEqualTo("Alin Chiperi");
        assertThat(result.getContent()).isEqualTo("Post content");
        assertThat(result.getUnderTitle()).isEqualTo("Post under title");
        assertThat(post.getCreatedOn()).isAfter(LocalDateTime.now().minus(1, ChronoUnit.MINUTES));
        assertThat(post.getCreatedOn()).isBefore(LocalDateTime.now());
    }

    private Post getPost() {
        return Post.builder()
                .id(1L)
                .title("Post title")
                .content("Post content")
                .createdBy("Alin Chiperi")
                .underTitle("Post under title")
                .createdOn(currentTime)
                .tags(List.of(
                        Tag.builder().id(1L).name("csm").build(),
                        Tag.builder().id(2L).name("Suceava").build()))
                .build();
    }

}
