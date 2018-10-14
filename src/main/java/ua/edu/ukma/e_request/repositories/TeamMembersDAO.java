package ua.edu.ukma.e_request.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.e_request.entities.TeamMembers;

import java.util.List;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@Repository
public interface TeamMembersDAO extends JpaRepository<TeamMembers, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM e_team_members WHERE request_id=?")
    List<TeamMembers> getTeamMembersByRequest(@Param("request_id") Long requestId);

    @Query(nativeQuery = true, value = "SELECT  * FROM e_team_members WHERE request_id=? AND user_id=?")
    TeamMembers getTeamMemberByRequestIdAndUserId(@Param("user_id") Long requestId, @Param("user_id") Long userId);
}
