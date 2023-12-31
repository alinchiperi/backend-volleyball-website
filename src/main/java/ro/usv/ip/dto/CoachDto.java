package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Coach;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
public class CoachDto {
    private Long id;
    private String firstName;
    private String lastName;
    private byte[] photo;
    private LocalDate dob;
    private String role;
    private String description;
    private String title;
    private String nationality;


    public static CoachDto from (Coach coach){
        return CoachDto.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .photo(coach.getPhoto())
                .role(coach.getRole())
                .dob(coach.getDob())
                .description(coach.getDescription())
                .title(coach.getTitle())
                .nationality(coach.getNationality())
                .build();
    }
}
