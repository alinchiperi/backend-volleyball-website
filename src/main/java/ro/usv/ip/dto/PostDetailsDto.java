package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.PostDetails;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDetailsDto {

    private Long id;
    private LocalDateTime createdOn;
    private String createdBy;

    public static PostDetailsDto from(PostDetails postDetails) {
        return PostDetailsDto.builder()
                .id(postDetails.getId())
                .createdOn(postDetails.getCreatedOn())
                .createdBy(postDetails.getCreatedBy())
                .build();
    }

}