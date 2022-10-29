package ro.usv.ip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Table
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastNamel;

    private LocalDate dob;

    private float height;

    private int shirtNumber;

    @Lob
    @Column(name = "photo", columnDefinition = "BLOB")
    private byte[] photo;

}
