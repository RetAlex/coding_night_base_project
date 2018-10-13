package ua.edu.ukma.e_request.globals.middlewares;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.UserContext;
import ua.edu.ukma.e_request.utils.exceptions.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestUpdateAccessMiddleware implements HandlerInterceptor {
    private final RequestService requestService;

    public RequestUpdateAccessMiddleware(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            return requestService.isAllowedToUpdateRequest(UserContext.getCurrentUserLogin(), Long.parseLong(request.getParameter("orderId")));
        }catch (NumberFormatException e){
            throw new BadRequestException("orderId");
        }
    }
}
