package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.entities.StatusChanges;
import ua.edu.ukma.e_request.entities.ThirdPartyToken;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.StatusChangesDAO;
import ua.edu.ukma.e_request.repositories.ThirdPartyTokenDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.resources.enums.RequestStatus;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.UserContext;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestDAO requestDao;

    private final ThirdPartyTokenDAO thirdPartyTokenDAO;

    private final StatusChangesDAO statusChangesDAO;

    public RequestServiceImpl(RequestDAO requestDao, UserDAO userDAO, ThirdPartyTokenDAO thirdPartyTokenDAO, StatusChangesDAO statusChangesDAO) {
        this.requestDao = requestDao;
        this.thirdPartyTokenDAO = thirdPartyTokenDAO;
        this.statusChangesDAO = statusChangesDAO;
    }

    @Override
    public long createRequest(CreateOrderController.CreateRequestForm createRequestForm, long studentId) {
        return requestDao.save(new Request(createRequestForm, studentId)).getId();
    }

    @Override
    public Request getRequestById(long requestId) throws RequestNotExistsException {
        return requestDao.findById(requestId).orElseThrow(RequestNotExistsException::new);
    }

    @Override
    public List<RequestMinInfo> getRequestsForUser(int page) {
        return requestDao.getRequestForUser(UserContext.getCurrentUserLogin(), page * 10);
    }

    @Override
    public boolean isAllowedToUpdateRequest(String username, long orderId) {
        return requestDao.isAllowedToUpdate(username, orderId);
    }

    @Override
    public void applyOrder(long requestId) throws RequestNotExistsException {
        Request current = requestDao.findById(requestId).orElseThrow(RequestNotExistsException::new);
        RequestStatus prev = current.getCurrentStatus();
        if (current.getCurrentStatus().equals(RequestStatus.APPROVED_BY_MENTOR)) {
            List<ThirdPartyToken> tokens = thirdPartyTokenDAO.getTokensByRequest(current.getId());
            if (tokens.size() == 0) current.setCurrentStatus(RequestStatus.APPROVED_BY_THIRD);
            current.setCurrentStatus(RequestStatus.APPROVED_BY_DEAN);
            statusChangesDAO.save(new StatusChanges(current, prev));
            requestDao.save(current);
            return;
        }
        current.setCurrentStatus(current.getCurrentStatus().getNextStatus());
        statusChangesDAO.save(new StatusChanges(current, prev));
        requestDao.save(current);
    }


    public void declineOrder(long requestId, String reason, boolean finalDecision) throws RequestNotExistsException {
        Request current = requestDao.findById(requestId).orElseThrow(RequestNotExistsException::new);
        RequestStatus prev = current.getCurrentStatus();
        if (finalDecision) current.setCurrentStatus(RequestStatus.NEW);
        else current.setCurrentStatus(current.getCurrentStatus().getPrevious());
        statusChangesDAO.save(new StatusChanges(current, prev));
        requestDao.save(current);
    }

    @Override
    public List<RequestMinInfo> getRequestsForAdmin(int page) {
        return null;
    }

    @Override
    public void assignToMentor(long orderId, long mentorId) {

    }
}
