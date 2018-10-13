package ua.edu.ukma.e_request.entities;

import ua.edu.ukma.e_request.resources.enums.PRMethods;

import javax.persistence.*;

/**
 * Created by nasti on 10/14/2018.
 */
@Entity(name = "RequestPRMethods")
@Table(name = "e_pr_methods")
public class RequestPRMethods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_pr_method_id")
    private Long id;

    @ManyToOne
    @JoinTable(name = "request_id")
    private Request request;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private PRMethods prMethod;
}
