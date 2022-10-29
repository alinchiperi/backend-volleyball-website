package ro.usv.ip.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class Sponsor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String siteLink;

    @Lob
    @Column(name = "logo", columnDefinition = "BLOB")
    private byte[] logo;

}
