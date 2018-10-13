package ua.edu.ukma.e_request.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nasti on 10/13/2018.
 */
@Entity
public class Room implements Serializable {
    @Id
    @Column(name = "room_name")
    private String name;
    private String building;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
