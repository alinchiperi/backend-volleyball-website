package ro.usv.ip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Table
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Team homeTeam;

    @OneToOne(
            mappedBy = "game",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )

    @LazyToOne(LazyToOneOption.NO_PROXY)
    private Team awayTeam;


    private LocalDate date;

    private String category;

    private String link;
}
