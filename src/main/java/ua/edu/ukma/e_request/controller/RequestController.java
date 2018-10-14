package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

@Controller
@RequestMapping("/e_request/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{requestId}")
    public String renderPage(@PathVariable("requestId") long requestId, Model model) throws RequestNotExistsException {
        model.addAttribute("request", requestService.getRequestById(requestId));
        return "e_request/request/show";
    }

    @GetMapping({"","/"})
    public String showAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model){
        model.addAttribute("orders", requestService.getRequestsForUser(page));
        return "";
    }
}
