package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.PostComment;

import java.util.Date;

@Data
@Builder
public class PostCommentDto {

    private Long id;
    private String review;
    private String createdBy;
    private Date createdOn;

    public static PostCommentDto from(PostComment postComment) {
        return PostCommentDto.builder()
                .id(postComment.getId())
                .review(postComment.getReview())
                .createdBy(postComment.getCreatedBy())
                .createdOn(postComment.getCreatedOn())
                .build();
    }
}