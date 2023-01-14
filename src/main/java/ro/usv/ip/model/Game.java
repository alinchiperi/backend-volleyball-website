package ro.usv.ip.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long homeTeamId;

    private Long awayTeamId;


    private LocalDateTime date;

    private String location;

    private String link;

    private int homeTeamScore;
    private int awayTeamScore;
}
