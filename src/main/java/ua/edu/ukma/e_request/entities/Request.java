package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity of Request for event organization
 * <p>
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Request")
@Table(name = "e_requests")
@Setter
@Getter
@NoArgsConstructor
@SqlResultSetMapping(
        name = "findAllRequestForUserMapping",
        classes = @ConstructorResult(
                targetClass = RequestMinInfo.class,
                columns = {
                        @ColumnResult(name = "eventName"),
                        @ColumnResult(name = "startDateTime"),
                        @ColumnResult(name = "finishDateTime"),
                        @ColumnResult(name = "purpose")
                }
        )
)
@NamedNativeQuery(name = "findAllRequestForUser",
        query = "SELECT " +
                "    u.event_name as eventName, " +
                "    u.start_date_time as startDateTime, " +
                "    u.finished_data_time as finishDateTime, " +
                "    u.puspose as purpose " +
                "FROM " +
                "    e_requests as u " +
                "where  u.param1=:param1 and i.param2=:param2", resultSetMapping = "findAllRequestForUserMapping"
)
public class Request implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;

    @Basic
    @CreationTimestamp
    @Column(nullable = false, insertable = false)
    private Timestamp creationTimestamp;

    @Column(nullable = false)
    private String eventName;

    @Basic
    @Column(nullable = false)
    private Timestamp startDateTime;
    @Basic
    @Column(nullable = false)
    private Timestamp finishDateTime;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomName;

    @ManyToOne
    @JoinColumn(name = "prep_room_id")
    private Room preparationRoomName;

    @Basic
    private Timestamp prepStartDateTime;
    @Basic
    private Timestamp prepFinishDateTime;

    private Integer expectedAmountOfInvolved;

    @Column(name = "purpose", columnDefinition = "TEXT")
    private String purpose;
    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "expected_result", columnDefinition = "TEXT")
    private String expectedResult;

    private Boolean isSecurityNeeded;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "third_party_token_request",
            joinColumns = { @JoinColumn(name = "request_id") },
            inverseJoinColumns = { @JoinColumn(name = "token") }
    )
    private Set<ThirdPartyToken> thirdSides = new HashSet<>();

    @OneToMany(mappedBy = "request")
    private Set<TechRequest> techRequests = new HashSet<>();

    public Request(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
        this.eventName = createRequestForm.getName();
        this.mentor = new User(createRequestForm.getCurator());
        this.student = new User(studentId);
        this.purpose = createRequestForm.getPurpose();
        this.expectedResult = createRequestForm.getExpectedResult();
        this.targetAudience = createRequestForm.getTargetAudience();
        this.isSecurityNeeded = createRequestForm.getIsSecurityNeeded();
        this.finishDateTime = createRequestForm.getEndDate();
        this.startDateTime = createRequestForm.getStartDate();
        this.expectedAmountOfInvolved = createRequestForm.getExpectedAmountOfInvolved();
        this.techRequests = createRequestForm.getTechRequests();
        this.prepFinishDateTime = createRequestForm.getPrepFinishDateTime();
        this.prepStartDateTime = createRequestForm.getPrepStartDateTime();
    }
}
