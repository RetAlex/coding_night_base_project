package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.ukma.e_request.services.interfaces.RequestService;

@Controller
@RequestMapping("/e_request/admin")
public class AdminRequestController {
    private final RequestService requestService;

    public AdminRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/all")
    public String getAllApplications(Model model){
        model.addAttribute("requests", requestService.getRequestsForAdmin(0));
        return "e_request/request/showAll";
    }

    @PostMapping("/applyRequest")
    public String applyRequest(@RequestParam("request_id") long requestId){
        requestService.applyOrder(requestId);
        return "redirect:/e_request/admin/all";
    }
}
