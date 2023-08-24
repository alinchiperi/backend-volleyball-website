package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Post;
import ro.usv.ip.model.PostComment;

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
    private String underTitle;
    private String content;
    private String createdBy;
    private String link;
    private String category;
    private List<PostCommentDto> comments;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .tags(post.getTags().stream()
                        .map(TagDto::from)
                        .collect(Collectors.toList()))
                .content(post.getContent())
                .underTitle(post.getUnderTitle())
                .createdOn(post.getCreatedOn())
                .createdBy(post.getCreatedBy())
                .link(post.getLink())
                .category(post.getCategory())
                .comments(post.getComments().stream()
                        .map(PostCommentDto::from)
                        .collect(Collectors.toList()))
                .build();
    }
}
