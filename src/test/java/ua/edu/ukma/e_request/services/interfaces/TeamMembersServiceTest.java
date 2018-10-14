package ua.edu.ukma.e_request.services.interfaces;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.edu.ukma.e_oss.config.EOssApplication;
import ua.edu.ukma.e_request.controller.RequestController;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.TeamMembersDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.services.implementations.TeamServiceImpl;

/**
 * Created by a.bondarenko on 10/14/2018.
 */
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EOssApplication.class)
public class TeamMembersServiceTest {
    @Autowired
    private TeamMembersDAO teamMembersDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RequestDAO requestDAO;

    private TeamServiceImpl teamService;

    @Before
    public void setUp() {
        teamService = new TeamServiceImpl(teamMembersDAO, userDAO, requestDAO);
    }

    private RequestController.AssignToTeamForm generateAssignToForm() {
        RequestController.AssignToTeamForm assignToTeamForm = new RequestController.AssignToTeamForm();
        assignToTeamForm.setUserId(1L);
        assignToTeamForm.setRequestId(1L);
        assignToTeamForm.setPhone("2582794587");
        return assignToTeamForm;
    }
    @Test
    public void testAddTeamMemberToTeam() {
        RequestController.AssignToTeamForm assignToTeamForm = generateAssignToForm();
        teamService.addToTeam(assignToTeamForm);
    }

    @Test
    public void testRemoveTeamMember() {
        teamService.removeFromTeam(1L, 1L);
    }

}
