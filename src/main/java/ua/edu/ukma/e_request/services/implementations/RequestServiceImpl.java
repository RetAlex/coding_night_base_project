package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestDAO requestDao;

    public RequestServiceImpl(RequestDAO requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public long createRequest(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
        return requestDao.save(new Request(createRequestForm, studentId)).getId();
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
