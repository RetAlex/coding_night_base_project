package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Room")
@Table(name = "e_rooms")
@Setter
@Getter
@NoArgsConstructor
public class Room implements Serializable {
    @Id
    @Column(name = "room_id")
    private Long id;
    private String name;
    private String building;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Override
    public String toString() {
        return String.format("name='%s', building='%s', owner_id='%d'", name, building, owner.getId());
    }
}
