package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.e_request.resources.enums.PRMethods;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity of Request for event organization
 *
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Request")
@Table(name = "e_requests")
@Setter
@Getter
@NoArgsConstructor
public class Request implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long id;

    @Basic
    @Column(nullable = false)
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
    @JoinColumn(name = "room_name")
    private Room roomName;

    @ManyToOne
    @JoinColumn(name = "prep_room_name")
    private Room preparationRoomName;

    @Basic
    private Timestamp prepStartDateTime;
    @Basic
    private Timestamp prepFinishDateTime;

    private Integer expectedAmountOfInvolved;

    @Column(name = "purpose", columnDefinition = "TEXT")
    private String purpose;

    private String targetAudience;

    @Column(name = "expected_result", columnDefinition = "TEXT")
    private String expectedResult;

    private Boolean isSecurityNeeded;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private User mentor;

    @ManyToOne
    @JoinColumn(name = "token")
    private ThirdPartyToken thirdSide = null;

    @OneToMany(mappedBy = "request")
    private Set<TechRequest> techRequests = new HashSet<>();

    @ElementCollection(targetClass = PRMethods.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "pr_methods", joinColumns = @JoinColumn(name = "reuest_id"))
    @Column(name = "pr_methods", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<PRMethods> prMethods = new HashSet<>();
}
