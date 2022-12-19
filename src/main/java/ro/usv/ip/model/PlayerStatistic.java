package ro.usv.ip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class PlayerStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer attacks;
    private Integer blocks;
    private Integer aces;

    @OneToOne
    @MapsId  // this if player statistic should have same id as player
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

}
