package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.Room;
import ua.edu.ukma.e_request.resources.dto.FondRoom;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {
    @Query(nativeQuery = true, name = "findRoomByBuild")
    FondRoom getByBuild(@Param("build") String username); // building equals username
}
