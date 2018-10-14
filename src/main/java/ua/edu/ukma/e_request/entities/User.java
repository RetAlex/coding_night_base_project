package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.edu.ukma.e_request.resources.dto.FondRoom;
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
@ToString
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "getAllRoomsMapping",
                classes = @ConstructorResult(
                        targetClass = FondRoom.class,
                        columns = {
                                @ColumnResult(name = "id",type = Long.class),
                                @ColumnResult(name = "name",type = String.class),
                                @ColumnResult(name = "building",type = String.class),
                                @ColumnResult(name = "username",type = String.class),
                        }
                )
        )})
@NamedNativeQueries({
        @NamedNativeQuery(name = "getAllRooms",
                query = "SELECT " +
                        "    r.room_id as id, " +
                        "    r.name as name, " +
                        "    r.building as building, " +
                        "    e.username as username " +
                        "FROM " +
                        "    e_users as e inner join e_rooms r on e.user_id=r.user_id" +
                        " where e.role='FOND'", resultSetMapping = "getAllRoomsMapping"
        )
})
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

    private String username;

    public User(long userId) {
        this.id = userId;
    }
}
