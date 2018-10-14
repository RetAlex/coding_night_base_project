package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.resources.enums.RequestStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
@SqlResultSetMappings({
@SqlResultSetMapping(
        name = "findAllRequestForUserMapping",
        classes = @ConstructorResult(
                targetClass = RequestMinInfo.class,
                columns = {
                        @ColumnResult(name = "eventName",type = String.class),
                        @ColumnResult(name = "startDateTime",type = Date.class),
                        @ColumnResult(name = "finishDateTime",type = Date.class),
                        @ColumnResult(name = "purpose",type = String.class),
                        @ColumnResult(name = "id",type = Long.class),
                        @ColumnResult(name = "currentStatus",type = RequestStatus.class)
                }
        )
)})
@NamedNativeQueries({
        @NamedNativeQuery(name = "findAllRequestForUser",
        query = "SELECT " +
                "    u.event_name as eventName, " +
                "    u.start_date_time as startDateTime, " +
                "    u.finish_date_time as finishDateTime, " +
                "    u.purpose as purpose, " +
                "    u.request_id as id," +
                "    u.current_status as currentStatus " +
                "FROM " +
                "    e_requests as u inner join e_users e on e.user_id=u.student_id" +
                " where e.username=:param1 limit 10 offset :param2", resultSetMapping = "findAllRequestForUserMapping"
),
        @NamedNativeQuery(name = "checkIsUpdate",
                query = "SELECT true " +
                        "FROM " +
                        "    e_requests as u inner join e_users e on e.user_id=u.student_id" +
                        " where e.username=:username and u.request_id=:requestId"
        ),

})
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

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private RequestStatus currentStatus;

    public Request(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
       this.eventName = createRequestForm.getTitle();
        this.mentor = new User(createRequestForm.getCurator());
       this.student = new User(studentId);
       this.purpose = createRequestForm.getAim();
 //      this.expectedResult = createRequestForm.getExpectedResult();
//       this.targetAudience = createRequestForm.get
//       this.isSecurityNeeded = createRequestForm.getIsSecurityNeeded();
        this.finishDateTime = createRequestForm.getDateTo();
        this.startDateTime = createRequestForm.getDateFrom();
//        this.expectedAmountOfInvolved = createRequestForm.getExpectedAmountOfInvolved();
//        this.techRequests = createRequestForm.getTechRequests();
       this.prepFinishDateTime = createRequestForm.getPrepDateTo();
       this.roomName = createRequestForm.getRoom();
       this.preparationRoomName = createRequestForm.getPrepRoom();
        this.prepStartDateTime = createRequestForm.getPrepDateFrom();
    }
}
