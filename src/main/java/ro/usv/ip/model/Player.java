package ro.usv.ip.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


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

    private int shirtNumber;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

    private String category;
    private String nationality;
    private String description;


}
