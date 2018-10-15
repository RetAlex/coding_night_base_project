package ua.edu.ukma.e_request.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.ukma.e_request.resources.enums.Role;
import ua.edu.ukma.e_request.services.FileStorageService;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.services.interfaces.TeamService;
import ua.edu.ukma.e_request.services.interfaces.UserService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

import java.io.IOException;

@Controller
@RequestMapping("/e_request/requests")
public class RequestController {
    private final RequestService requestService;
    private final TeamService teamService;
    private final UserService userService;
    private final FileStorageService fileStorageService;

    public RequestController(RequestService requestService, TeamService teamService, UserService userService, FileStorageService fileStorageService) {
        this.requestService = requestService;
        this.teamService = teamService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{requestId}")
    public String renderPage(@PathVariable("requestId") long requestId, Model model) throws RequestNotExistsException {
        model.addAttribute("request", requestService.getRequestById(requestId));
        //TODO make this more efficient by retrieving only id and username
        model.addAttribute("assignableUsers", userService.getUserByRole(Role.STUDENT));
        //TODO do this only if status == NEW
        model.addAttribute("assignableMentors", userService.getUserByRole(Role.MENTOR));
        model.addAttribute("team", teamService.getByRequest(requestId));
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

    @PostMapping("/upload_file")
    public String saveFiles(@RequestParam("file") MultipartFile file, Model model, @RequestParam(name = "userId") long userId, @RequestParam(name = "eventId") long eventId) {
        if (file.isEmpty() || file.getOriginalFilename()==null) {
            model.addAttribute("error", "errors.fileEmpty");
            return "redirect:/e_request/requests/"+eventId;
        }
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')+1);
        if(extension.isEmpty()) extension="txt";
        try {
            fileStorageService.saveFile(userId, eventId, file.getBytes(), extension);
        } catch (IOException e) {
            model.addAttribute("error", "errors.invalidFile");
            return "redirect:/e_request/requests/"+eventId;
        }
        return "redirect:/e_request/requests/"+eventId;
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