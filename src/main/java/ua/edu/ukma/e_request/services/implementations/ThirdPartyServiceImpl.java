package ua.edu.ukma.e_request.services.implementations;

import org.springframework.stereotype.Service;
import ua.edu.ukma.e_request.entities.Request;
import ua.edu.ukma.e_request.services.interfaces.ThirdPartyService;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Override
    public Request getRequestToApprove(String token) {
        return null;
    }

    @Override
    public void approveRequest(String token, long requestId) {

    }

    @Override
    public void disapproveRequest(String token, long requestId) {

    }
}
