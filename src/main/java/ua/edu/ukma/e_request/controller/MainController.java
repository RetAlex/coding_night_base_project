package ua.edu.ukma.e_request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * To be renamed
 */
@Controller
@RequestMapping("/e_request")
public class MainController {
    @GetMapping({"", "/"})
    public String renderPage(){
        return "e_request/test";
    }
}
