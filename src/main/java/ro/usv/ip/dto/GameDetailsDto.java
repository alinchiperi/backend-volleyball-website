package ro.usv.ip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.usv.ip.model.Team;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GameDetailsDto {
    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime date;
    private String location;
}
