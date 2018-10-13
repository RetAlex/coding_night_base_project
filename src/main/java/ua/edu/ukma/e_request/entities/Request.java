package ua.edu.ukma.e_request.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by nasti on 10/13/2018.
 */
@Entity(name = "Request")
@Table(name = "e_requests")
public class Request implements Serializable{
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

}
