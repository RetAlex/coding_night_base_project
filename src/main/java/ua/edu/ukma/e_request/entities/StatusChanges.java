package ua.edu.ukma.e_request.entities;

import ua.edu.ukma.e_request.resources.enums.RequestStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by a.bondarenko on 10/14/2018.
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
    @JoinColumn(name = "prev_change_id")
    private StatusChanges previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private RequestStatus status;

    @ManyToOne
    @JoinTable(name = "request_id")
    private Request request;

    @Column(columnDefinition = "TEXT")
    private String refusalDescription;

    @Override
    public String toString() {
        return String.format(
                "id=%d, timestamp=%s, previousStatusId=%d, status=%s, request_id=%d, refusalDescription=%s",
                id,
                timestamp.toString(),
                previousStatus.id,
                status.name(),
                request.getId(),
                refusalDescription
        );
    }
}
