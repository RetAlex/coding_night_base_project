package ua.edu.ukma.e_request.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Team")
@Table(name = "e_teams")
public class Team implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private Long id;
    private String name;
}
