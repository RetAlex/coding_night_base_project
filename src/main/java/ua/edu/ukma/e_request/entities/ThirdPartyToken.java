package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    private String token;
    @Basic
    @Column(nullable = false)
    private Timestamp expirationDateTime;
}
