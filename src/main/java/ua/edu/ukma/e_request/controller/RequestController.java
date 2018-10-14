package ua.edu.ukma.e_request.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.e_request.resources.enums.Role;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.services.interfaces.TeamService;
import ua.edu.ukma.e_request.services.interfaces.UserService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

@Controller
@RequestMapping("/e_request/requests")
public class RequestController {
    private final RequestService requestService;
    private final TeamService teamService;
    private final UserService userService;

    public RequestController(RequestService requestService, TeamService teamService, UserService userService) {
        this.requestService = requestService;
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping("/{requestId}")
    public String renderPage(@PathVariable("requestId") long requestId, Model model) throws RequestNotExistsException {
        model.addAttribute("request", requestService.getRequestById(requestId));
        //TODO make this more efficient by retrieving only id and username
        model.addAttribute("assignableUsers", userService.getUserByRole(Role.STUDENT));
        //TODO do this only if status == NEW
        model.addAttribute("assignableMentors", userService.getUserByRole(Role.MENTOR));
        return "e_request/request/show";
    }

    @GetMapping({"","/"})
    public String showAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model){
        model.addAttribute("requests", requestService.getRequestsForUser(page));
        return "e_request/request/showAll";
    }

    @PostMapping("/assignToTeam")
    public String assignMember(@ModelAttribute AssignToTeamForm assignToTeamForm){
        teamService.addToTeam(assignToTeamForm);
        return "redirect:/e_request/requests/"+assignToTeamForm.requestId;
    }

    @PostMapping("/removeFromTeam")
    public String assignToTeam(@RequestParam("userId") long userId, @RequestParam("requestId") long requestId){
        teamService.removeFromTeam(userId, requestId);
        return "redirect:/e_request/requests/"+requestId;
    }

    @PostMapping("/assignToMentor")
    public String assignToMentor(@RequestParam("requestId") long requestId, @RequestParam("mentorId") long mentorId){
        requestService.assignToMentor(requestId, mentorId);
        return "redirect:/e_request/requests/"+requestId;
    }

    @Data
    @AllArgsConstructor
    public static class AssignToTeamForm{
        private long userId;
        private long requestId;
        private String phone;
        private String responsibility;
        private String specialization;
        private double hours;

        public AssignToTeamForm() {
        }
    }
}