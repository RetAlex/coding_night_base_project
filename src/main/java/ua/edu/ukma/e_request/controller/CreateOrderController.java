package ua.edu.ukma.e_request.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.e_request.entities.Room;
import ua.edu.ukma.e_request.resources.enums.PRMethods;
import ua.edu.ukma.e_request.resources.enums.Role;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.services.interfaces.UserService;
import ua.edu.ukma.e_request.utils.validator.InFuture;
import ua.edu.ukma.e_request.utils.validator.UserRole;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Controller
@RequestMapping("/e_request")
public class CreateOrderController {
    private final RequestService requestService;
    private final UserService userService;

    public CreateOrderController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String renderPage(Model model){
        model.addAttribute("assignableMentors", userService.getUserByRole(Role.MENTOR));
        return "e_request/creation/create_request";
    }

    @PostMapping("/create")
    public String createRequest(@Valid @ModelAttribute CreateRequestForm createRequestForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
        }
        //TODO add student session id
        requestService.createRequest(createRequestForm,1  );
        return "e_request/creation/request_created";
    }

    @Data
    @NoArgsConstructor
    public static class CreateRequestForm{
        @NotNull
        @Size(min=5, max = 30)
        private String title;
        @NotNull
        @InFuture
        private Timestamp dateFrom;
        @NotNull
        @InFuture
        private Timestamp dateTo;
        private Set<PRMethods> pr;
        @NotNull
        @UserRole(requiredRole = {Role.MENTOR})
        private long curator;

        @Size( max = 100)
        private String desc;
        private String aim;
        private String audition;
        private Room room;
        private Room prepRoom;
        private Timestamp prepDateFrom;
        private Timestamp prepDateTo;
        private int participants;
    }
}
