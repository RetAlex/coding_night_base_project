package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.controller.CreateRequestController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.entities.StatusChanges;
import ua.edu.ukma.e_request.entities.ThirdPartyToken;
import ua.edu.ukma.e_request.entities.User;
import ua.edu.ukma.e_request.repositories.RequestDAO;
import ua.edu.ukma.e_request.repositories.StatusChangesDAO;
import ua.edu.ukma.e_request.repositories.ThirdPartyTokenDAO;
import ua.edu.ukma.e_request.repositories.UserDAO;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.resources.enums.RequestStatus;
import ua.edu.ukma.e_request.services.interfaces.RequestService;
import ua.edu.ukma.e_request.utils.UserContext;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;
import ua.edu.ukma.e_request.utils.exceptions.UserNotFoundException;
import ua.edu.ukma.e_request.utils.logger.Logger;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestDAO requestDao;
    private final UserDAO userDAO;

    private final ThirdPartyTokenDAO thirdPartyTokenDAO;

    private final StatusChangesDAO statusChangesDAO;

    public RequestServiceImpl(RequestDAO requestDao, UserDAO userDAO, ThirdPartyTokenDAO thirdPartyTokenDAO, StatusChangesDAO statusChangesDAO) {
        this.requestDao = requestDao;
        this.userDAO = userDAO;
        this.thirdPartyTokenDAO = thirdPartyTokenDAO;
        this.statusChangesDAO = statusChangesDAO;
    }

    @Override
    public long createRequest(CreateRequestController.CreateRequestForm createRequestForm, long studentId) {
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
    public void assignToMentor(long requestId, long mentorId) {
        try {
            findRequestAndAssignToMentorIfStatusIsNew(requestId, mentorId);
            Logger.log("assignToMentor :: pending status and new mentor was set to request");
        } catch (RequestNotExistsException | UserNotFoundException e) {
            Logger.logException("assignToMentor :: failed to complete operation", e, true);
        }
    }

    private void findRequestAndAssignToMentorIfStatusIsNew(long requestId, long mentorId) throws RequestNotExistsException, UserNotFoundException {
        Request request = getRequestById(requestId);
        if (RequestStatus.NEW.equals(request.getCurrentStatus())) {
            request.setCurrentStatus(RequestStatus.PENDING_FOR_SUBMITION);
            createNewStatusChange(request, RequestStatus.NEW);
            request.setMentor(getUserById(mentorId));
        }
        requestDao.flush();
    }

    private void createNewStatusChange(Request request, RequestStatus previousStatusChange) {
        StatusChanges statusChanges = new StatusChanges(request, previousStatusChange);
        statusChangesDAO.saveAndFlush(statusChanges);
        Logger.log("createNewStatusChange :: add new status change : " + statusChanges);
    }

    private User getUserById(long userID) throws UserNotFoundException {
        return userDAO.findById(userID).orElseThrow(() -> new UserNotFoundException(userID));
    }
}
