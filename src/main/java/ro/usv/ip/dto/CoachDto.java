package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Coach;

import javax.persistence.*;

@Data
@Builder
public class CoachDto {
    private Long id;
    private String firstName;
    private String lastName;
    private byte[] photo;

    public static CoachDto from (Coach coach){
        return CoachDto.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .photo(coach.getPhoto())
                .build();
    }
}
