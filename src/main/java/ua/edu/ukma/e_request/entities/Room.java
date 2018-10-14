package ua.edu.ukma.e_request.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.edu.ukma.e_request.resources.dto.FondRoom;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
@Entity(name = "Room")
@Table(name = "e_rooms")
@Setter
@Getter
@NoArgsConstructor
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "findRoomByBuildMapping",
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
        @NamedNativeQuery(name = "findRoomByBuild",
                query = "SELECT " +
                        "    r.room_id as id, " +
                        "    r.name as name, " +
                        "    r.building as building, " +
                        "    e.username as username " +
                        "FROM " +
                        "    e_rooms as r inner join e_users e on e.user_id=r.user_id" +
                        " where e.username=:username", resultSetMapping = "findRoomByBuildMapping"
        )
})
public class Room implements Serializable {
    @Id
    @Column(name = "room_id")
    private Long id;
    private String name;
    private String building;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Override
    public String toString() {
        return String.format("name='%s', building='%s', owner_id='%d'", name, building, owner.getId());
    }

    public Room(Long id) {
        this.id = id;
    }
}
