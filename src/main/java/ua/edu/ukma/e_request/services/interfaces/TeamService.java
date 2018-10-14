package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.controller.RequestController;

public interface TeamService {
    void addToTeam(RequestController.AssignToTeamForm assignToTeamForm);
    void removeFromTeam(long userId, long requestId);
}
