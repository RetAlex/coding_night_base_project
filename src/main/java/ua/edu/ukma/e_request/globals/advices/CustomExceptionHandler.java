package ua.edu.ukma.e_request.globals.advices;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.edu.ukma.e_request.utils.exceptions.BadRequestException;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestNotExistsException.class)
    public String handleUserNotExistsException(){
        return "e_request/errors/order_not_exists";
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleUserNotExistsException(BadRequestException ex, Model model){
        model.addAttribute("error", "Параметр "+ex.getMessage()+" відсутній.");
        return "e_request/errors/order_not_exists";
    }
}