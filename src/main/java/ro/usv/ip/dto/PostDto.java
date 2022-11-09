package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class PostDto {
    private Long id;
    private String title;
    private List<TagDto> tags;
    private LocalDateTime createdOn;
    private String content;
    private String createdBy;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .tags(post.getTags().stream()
                        .map(TagDto::from)
                        .collect(Collectors.toList()))
                .content(post.getContent())
                .build();
    }
}
