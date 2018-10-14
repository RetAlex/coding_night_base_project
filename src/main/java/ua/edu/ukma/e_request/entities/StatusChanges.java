package ua.edu.ukma.e_request.entities;

import org.hibernate.annotations.CreationTimestamp;
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
    @CreationTimestamp
    private Timestamp timestamp;

    @OneToOne
    @JoinColumn(name = "prev_change_id")
    private RequestStatus previousStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @Column(columnDefinition = "TEXT")
    private String refusalDescription;

    public StatusChanges(Request current, RequestStatus previousStatus) {
        this.status = current.getCurrentStatus();
        this.request = current;
        this.previousStatus = previousStatus;
    }

    @Override
    public String toString() {
        String previousStatusId = (previousStatus == null) ? "null" : String.valueOf(previousStatus);
        return String.format(
                "id=%d, timestamp=%s, previousStatusId=%s, status=%s, request_id=%d, refusalDescription=%s",
                id,
                String.valueOf(timestamp),
                previousStatusId,
                status.name(),
                request.getId(),
                refusalDescription
        );
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
