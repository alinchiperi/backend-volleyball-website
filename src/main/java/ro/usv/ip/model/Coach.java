package ro.usv.ip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "team_id")
//    private Team team;


}
