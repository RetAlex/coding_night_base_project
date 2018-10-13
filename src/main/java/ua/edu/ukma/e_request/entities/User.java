package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.e_request.resources.enums.Role;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Test User instance
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "User")
@Table(name = "e_users")
@Setter
@Getter
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private Role role;

    private String email;

    private String firstName;
    private String middleName;
    private String lastName;

    public User(long userId) {
        this.id = userId;
    }
}
