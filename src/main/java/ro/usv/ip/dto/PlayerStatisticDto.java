package ro.usv.ip.dto;

import lombok.Builder;
import lombok.Data;
import ro.usv.ip.model.PlayerStatistic;

@Data
@Builder
public class PlayerStatisticDto {

    private Long id;
    private Integer attacks;
    private Integer blocks;
    private Integer aces;
    private Long playerId;

    public static PlayerStatisticDto from(PlayerStatistic playerStatistic){
        return PlayerStatisticDto.builder()
                .id(playerStatistic.getId())
                .attacks(playerStatistic.getAttacks())
                .blocks(playerStatistic.getBlocks())
                .aces(playerStatistic.getAces())
                .playerId(playerStatistic.getPlayer().getId())
                .build();

    }
}
