package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Tech")
@Table(name = "e_tech")
@Setter
@Getter
@NoArgsConstructor
public class Tech implements Serializable{
    @Id
    @Column(name = "tech_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}
