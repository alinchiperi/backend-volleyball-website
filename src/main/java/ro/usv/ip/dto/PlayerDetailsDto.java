package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Player;
import ro.usv.ip.model.PlayerStatistic;

import java.time.LocalDate;

@Data
@Builder
public class PlayerDetailsDto {
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
    private Integer attacks;
    private Integer blocks;
    private Integer aces;

    public static PlayerDetailsDto from (Player player, PlayerStatistic playerStatistic){
        return PlayerDetailsDto.builder()
                .id(player.getId())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .dob(player.getDob())
                .height(player.getHeight())
                .shirtNumber(player.getShirtNumber())
                .photo(player.getPhoto())
                .category(player.getCategory())
                .nationality(player.getNationality())
                .description(player.getDescription())
                .position(player.getPosition())
                .title(player.getTitle())
                .weight(player.getWeight())
                .aces(playerStatistic.getAces())
                .blocks(playerStatistic.getBlocks())
                .attacks(playerStatistic.getAttacks())
                .build();
    }


}
