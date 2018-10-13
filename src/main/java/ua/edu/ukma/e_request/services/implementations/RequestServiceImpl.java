package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Override
    public long createRequest(CreateOrderController.CreateRequestForm createRequestForm) {
        return 0;
    }

    @Override
    public Request getRequestById(long requestId) throws RequestNotExistsException {
        return null;
    }

    @Override
    public List<RequestMinInfo> getRequestsForUser(int page) {
        return null;
    }
}
