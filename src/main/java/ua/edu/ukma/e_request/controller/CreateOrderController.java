package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.e_request.services.interfaces.RequestService;

import javax.validation.Valid;

@Controller
@RequestMapping("/e_request")
public class CreateOrderController {
    private final RequestService requestService;

    public CreateOrderController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping({"", "/"})
    public String renderPage(){
        return "e_request/creation/create_request";
    }

    @PostMapping("/create")
    public String createRequest(@Valid @ModelAttribute CreateRequestForm createRequestForm, BindingResult bindingResult){
        return "e_request/creation/request_created";
    }

    public static class CreateRequestForm{

    }
}
