
package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;
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

    @GetMapping("/{orderId}")
    public String showOrder(Model model, @PathVariable("orderId") long orderId) throws RequestNotExistsException {
        model.addAttribute("request", requestService.getRequestById(orderId));
        return "e_request/request/adminShow";
    }

    @PostMapping("/applyRequest")
    public String applyRequest(@RequestParam("request_id") long requestId) throws RequestNotExistsException {
        requestService.applyOrder(requestId);
        return "redirect:/e_request/admin/all";
    }

    @PostMapping("/declineRequest")
    public String declineRequest(@RequestParam("request_id") long requestId, @RequestParam("reason") String reason, @RequestParam(value = "final", required = false, defaultValue = "false") boolean finalDecision) throws RequestNotExistsException {
        requestService.declineOrder(requestId, reason, finalDecision);
        return "redirect:/e_request/admin/all";
    }

    @PostMapping("/{requestId}/inviteThird")
    public String inviteThird(@RequestParam String email, @PathVariable("requestId") long requestId){
        requestService.addThirdPartyToken(email, requestId);
        return "";
    }
}