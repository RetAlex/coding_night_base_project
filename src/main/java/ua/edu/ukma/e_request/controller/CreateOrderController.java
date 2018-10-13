package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.e_request.resources.enums.PRMethods;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.validator.InFuture;
import ua.edu.ukma.e_request.utils.validator.UserRole;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/e_request")
public class CreateOrderController {
    private final RequestService requestService;

    public CreateOrderController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/create")
    public String renderPage(){
        return "e_request/creation/create_request";
    }

    @PostMapping("/create")
    public String createRequest(@Valid @ModelAttribute CreateRequestForm createRequestForm, BindingResult bindingResult){
        return "e_request/creation/request_created";
    }

    public static class CreateRequestForm{
        @NotNull
        @Size(min=5, max = 30)
        private String name;
        @NotNull
        @Size(min=20, max = 5000)
        private String description;
        @NotNull
        @InFuture
        private Date startDate;
        @NotNull
        @InFuture
        private Date endDate;
        @NotNull
        private List<PRMethods> prMethods;
        //TODO Change to team entity object
        private List<Object> team;
        @NotNull
        @UserRole()
        private long curator;
    }
}
