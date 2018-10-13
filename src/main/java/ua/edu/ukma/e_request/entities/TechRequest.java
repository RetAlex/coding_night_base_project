package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "TechRequest")
@Table(name = "e_tech_request")
@Setter
@Getter
@NoArgsConstructor
public class TechRequest implements Serializable{
    @Id
    @Column(name = "tech_request_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id")
    private Request request;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tech_id")
    private Tech tech;
    private Integer status;
}
