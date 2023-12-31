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
    private Long id;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime date;
    private String location;
    private int homeTeamScore;
    private int awayTeamScore;
    private String link;
    private String linkFederatie;
}
