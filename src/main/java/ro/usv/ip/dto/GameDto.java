package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Game;

import java.time.LocalDateTime;

@Data
@Builder
public class GameDto {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;
    private LocalDateTime date;
    private String location;

    public static GameDto from(Game game){
        return GameDto.builder()
                .id(game.getId())
                .homeTeamId(game.getHomeTeamId())
                .awayTeamId(game.getAwayTeamId())
                .date(game.getDate())
                .location(game.getLocation())
                .build();
    }

}
