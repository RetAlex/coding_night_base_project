package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.RequestController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.entities.TeamMembers;
import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.TeamMembersDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.services.interfaces.TeamService;
import ua.edu.ukma.e_request.utils.logger.Logger;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamMembersDAO teamMembersDAO;
    private final UserDAO userDAO;
    private final RequestDAO requestDAO;

    public TeamServiceImpl(TeamMembersDAO teamMembersDAO, UserDAO userDAO, RequestDAO requestDAO) {
        this.teamMembersDAO = teamMembersDAO;
        this.userDAO = userDAO;
        this.requestDAO = requestDAO;
    }

    @Override
    public void addToTeam(RequestController.AssignToTeamForm assignToTeamForm) {
        try {
            createTeamInstanceAndInsertToData(assignToTeamForm);
            Logger.log("addToTeam");
        } catch (Exception e) {
            String message = getClass().getCanonicalName() + " :: addToTeam :: Failed to insert new team member to database";
            Logger.logException(message, e, true);
        }
    }

    private void createTeamInstanceAndInsertToData(RequestController.AssignToTeamForm assignToTeamForm) throws NullPointerException {
        TeamMembers teamMember = createTeamMember(assignToTeamForm);
        teamMembersDAO.saveAndFlush(teamMember);
    }

    private TeamMembers createTeamMember(RequestController.AssignToTeamForm assignToTeamForm) throws NullPointerException {
        TeamMembers teamMember = new TeamMembers();
        User userById = userDAO.getUserById(assignToTeamForm.getUserId());
        if (userById == null) {
            throw new NullPointerException("No user is found with id=" + assignToTeamForm.getUserId() + "\nAssign to ");
        }
        teamMember.setUser(userById);
        teamMember.setRequest(getRequest(assignToTeamForm));
        teamMember.setResponsibility(assignToTeamForm.getResponsibility());
        teamMember.setPhoneNumber(assignToTeamForm.getPhone());
        teamMember.setSpecialisation(assignToTeamForm.getSpecialization());
        return teamMember;
    }

    private Request getRequest(RequestController.AssignToTeamForm assignToTeamForm) throws NullPointerException {
        Long requestId = assignToTeamForm.getRequestId();
        if (requestId == null) {
            String message = "getRequest :: AssignToTeamForm.requestId=null, filed is required to be not null";
            throw  new NullPointerException(message);
        }
        Request request = requestDAO.getRequestById(requestId);
        if (request == null) {
            String message = "getRequest :: RequestDAO :: getRequestById :: User with id=" + requestId + " is not found";
            throw  new NullPointerException(message);
        }
        return request;
    }

    @Override
    public void removeFromTeam(long userId, long requestId) {
        TeamMembers member = teamMembersDAO.getTeamMemberByRequestIdAndUserId(requestId, userId);
        teamMembersDAO.delete(member);
    }
}
