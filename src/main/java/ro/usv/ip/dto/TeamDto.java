package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.Team;

@Builder
@Data
public class TeamDto {
    private Long id;

    private String name;

    private byte[] logo;
    private String location;

    public static TeamDto from (Team team){
        return TeamDto.builder()
                .id(team.getId())
                .logo(team.getLogo())
                .name(team.getName())
                .location(team.getLocation())
                .build();
    }
}
