package ro.usv.ip.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private LocalDate dob;

    private float height;
    private float weight;

    private int shirtNumber;

    @Lob
    @Column(name = "photo", columnDefinition = "LONGBLOB")
    private byte[] photo;

    private String category;
    private String nationality;
    private String title;
    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String position;

    @OneToMany(mappedBy = "player",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<PlayerStatistic> statistics;


}
