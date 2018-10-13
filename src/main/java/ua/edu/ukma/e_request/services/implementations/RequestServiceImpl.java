package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.services.interfaces.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public long createRequest(CreateOrderController.CreateRequestForm createRequestForm) {
        return 0;
    }
}
