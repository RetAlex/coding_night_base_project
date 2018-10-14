package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.RequestController;
import ua.edu.ukma.e_request.services.interfaces.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    @Override
    public void addToTeam(RequestController.AssignToTeamForm assignToTeamForm) {

    }

    @Override
    public void removeFromTeam(long userId, long requestId) {

    }
}
