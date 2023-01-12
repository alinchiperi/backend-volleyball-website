package ro.usv.ip.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    @Column(name = "logo", columnDefinition = "BLOB")
    private byte[] logo;

    private String location;

//    @OneToOne(mappedBy = "team")
//    private Coach coach;

}
