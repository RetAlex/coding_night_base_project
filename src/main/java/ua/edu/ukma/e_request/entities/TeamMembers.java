package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity represents Team member field in request form
 *
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "TeamUsers")
@Table(name = "e_team_members")
@Setter
@Getter
@NoArgsConstructor
public class TeamMembers implements Serializable{
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

    private double hours;

    @Override
    public String toString() {
        return String.format("id=$d, user_id=%d, request_id=%d, responsibility=%s, phoneNumber=%s, specialisation=%s",
                id, user.getId(), request.getId(), responsibility, phoneNumber, specialisation);
    }
}
