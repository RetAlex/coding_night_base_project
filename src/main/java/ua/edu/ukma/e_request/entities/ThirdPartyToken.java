package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nasti on 10/13/2018.
 */
@Entity(name = "ThirdPartyToken")
@Table(name = "e_third_party_token")
@Setter
@Getter
@NoArgsConstructor
public class ThirdPartyToken implements Serializable {
    @Id
    @Column(length = 100)
    private String token;
    @Basic
    @Column(nullable = false)
    private Timestamp expirationDateTime;

    @ManyToMany(mappedBy = "thirdSides")
    private Set<Request> requests = new HashSet<>();
}
