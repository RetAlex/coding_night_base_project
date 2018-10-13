package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity represents Team field in request form
 *
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    private String responsibility;

    private String phoneNumber;

    private String specialisation;
}
