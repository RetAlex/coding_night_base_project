package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.controller.RequestController;
import ua.edu.ukma.e_request.entities.TeamMembers;

import java.util.List;

public interface TeamService {
    void addToTeam(RequestController.AssignToTeamForm assignToTeamForm);
    void removeFromTeam(long userId, long requestId);
    List<TeamMembers> getByRequest(long requestId);
}
