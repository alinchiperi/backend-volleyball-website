package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Player;


import java.time.LocalDate;

@Data
@Builder
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private float height;
    private float weight;
    private int shirtNumber;
    private String category;
    private byte[] photo;
    private String nationality;
    private String description;
    private String position;
    private String title;

    public static PlayerDto from (Player player){
        return PlayerDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .dob(player.getDob())
                .height(player.getHeight())
                .weight(player.getWeight())
                .shirtNumber(player.getShirtNumber())
                .photo(player.getPhoto())
                .category(player.getCategory())
                .nationality(player.getNationality())
                .description(player.getDescription())
                .position(player.getPosition())
                .title(player.getTitle())
                .build();
    }
}
