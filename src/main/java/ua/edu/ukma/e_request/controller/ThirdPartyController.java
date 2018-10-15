package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.e_request.services.interfaces.ThirdPartyService;

@Controller
@RequestMapping("/e_request/third_party")
public class ThirdPartyController {
    private final ThirdPartyService thirdPartyService;

    public ThirdPartyController(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @GetMapping("/{token}/")
    public String getPageForToken(@PathVariable("token") String token, Model model){
        model.addAttribute("request", thirdPartyService.getRequestToApprove(token));
        //TODO add view for third party review
        return "";
    }
    @PostMapping("/{token}/approve")
    public String approve(@RequestParam("request_id") long requestId, @PathVariable("token") String token){
        thirdPartyService.approveRequest(token, requestId);
        return "";
    }

    @PostMapping("/{token}/disapprove")
    public String disapprove(@RequestParam("request_id") long requestId, @PathVariable("token") String token){
        thirdPartyService.disapproveRequest(token, requestId);
        return "";
    }
}
