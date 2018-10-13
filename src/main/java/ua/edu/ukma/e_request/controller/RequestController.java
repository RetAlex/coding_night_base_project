package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.e_request.utils.UserContext;

@Controller
@RequestMapping("/e_request/requests")
public class RequestController {
    @GetMapping("/{requestId}")
    public String renderPage(@PathVariable("requestId") long requestId){
        String login = UserContext.getCurrentUserLogin();
        System.out.println(login);
        return "e_request/request/show";
    }
}
