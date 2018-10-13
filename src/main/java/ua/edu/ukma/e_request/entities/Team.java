package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Team")
@Table(name = "e_teams")
@Setter
@Getter
@NoArgsConstructor
public class Team implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "teams")
    private Set<User> teamMembers = new HashSet<>();
}
