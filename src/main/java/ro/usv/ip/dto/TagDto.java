package ro.usv.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.usv.ip.model.Tag;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TagDto {

    private Long id;
    private String name;

    public static TagDto from(Tag tag) {
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public Tag toTag() {
        return new Tag(id, name);
    }

}
