package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.logger.Logger;

@Service
public class RequestServiceImpl implements RequestService {

    private RequestDAO requestDao;

    private UserDAO userDAO;

    @Override
    public long createRequest(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
        return requestDao.save(new Request(createRequestForm, studentId)).getId();
    }
}
