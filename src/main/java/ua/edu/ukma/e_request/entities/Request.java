package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.e_request.controller.CreateOrderController;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Request")
@Table(name = "e_requests")
@Setter
@Getter
@NoArgsConstructor
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;
    private String eventName;
    private String eventDescription;
    private Date startDate;
    private Date finishDate;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User student;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User mentor;
    @ManyToOne
    @JoinColumn(name = "room_name")
    private Room roomName;
    private String thirdSide = null;
    @OneToMany(mappedBy = "request")
    private Set<TechRequest> techRequests = new HashSet<>();

    public Request(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
        this.eventName = createRequestForm.getName();
        this.eventDescription = createRequestForm.getDescription();
        this.mentor = new User(createRequestForm.getCurator());
        this.student = new User(studentId);
    }
}
