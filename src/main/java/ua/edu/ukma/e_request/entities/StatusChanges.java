package ua.edu.ukma.e_request.entities;

import ua.edu.ukma.e_request.resources.enums.RequestStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by nasti on 10/14/2018.
 */
@Entity(name = "StatusChanges")
@Table(name = "e_status_changes")
public class StatusChanges implements Serializable{
    @Id
    @Column(name = "change_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "change_id")
    private StatusChanges previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private RequestStatus status;

    @ManyToOne
    @JoinTable(name = "request_id")
    private Request request;
}
