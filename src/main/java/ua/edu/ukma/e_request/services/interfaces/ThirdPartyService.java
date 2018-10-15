package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.entities.Request;

public interface ThirdPartyService {
    /**
     * Get request object to show page to third party member so he can overview of request
     * @param token token from email to get access
     * @return Request object bound to token
     */
    Request getRequestToApprove(String token);

    /**
     * This method checks if token is bound to request identified by id and if yes - approves it.
     * @param token token to grant access to request
     * @param requestId request to approve
     */
    void approveRequest(String token, long requestId);
    /**
     * This method checks if token is bound to request identified by id and if yes - disapproves it.
     * @param token token to grant access to request
     * @param requestId request to disapprove
     */
    void disapproveRequest(String token, long requestId);
}
