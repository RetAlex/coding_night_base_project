package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.resources.dto.RequestMinInfo;
import ua.edu.ukma.e_request.utils.exceptions.RequestNotExistsException;

import java.util.List;

public interface RequestService {
    /**
     * @param createRequestForm - form registration form. Until finished, class fields could be changed.
     * @return long id of request
     */
    long createRequest(CreateOrderController.CreateRequestForm createRequestForm, long studentId);

    Request getRequestById(long requestId) throws RequestNotExistsException;

    List<RequestMinInfo> getRequestsForUser(int page);

    /**
     * This method checks if specified user can update specified order
     * @param username user that requests update
     * @param orderId order to update
     * @return true if user has access to order
     */
    default boolean isAllowedToUpdateRequest(String username, long orderId) { return true; }

    void applyOrder(long requestId) throws RequestNotExistsException;

    void declineOrder(long requestId, String reason, boolean finalDecision) throws RequestNotExistsException;
    List<RequestMinInfo> getRequestsForAdmin(int page);

    void assignToMentor(long orderId, long mentorId);
}
