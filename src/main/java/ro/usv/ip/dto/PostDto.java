package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class PostDto {
    private Long id;
    private String title;
    private List<PostCommentDto> comments;
    private PostDetailsDto details;
    private List<TagDto> tags;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .details(Optional.ofNullable(post.getDetails())
                        .map(PostDetailsDto::from)
                        .orElse(null))
                .tags(post.getTags().stream()
                        .map(TagDto::from)
                        .collect(Collectors.toList()))
                .comments(post.getComments().stream()
                        .map(PostCommentDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
