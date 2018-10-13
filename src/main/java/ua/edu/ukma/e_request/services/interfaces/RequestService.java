package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.controller.CreateOrderController;

public interface RequestService {
    /**
     *
     * @param createRequestForm - form registration form. Until finished, class fields could be changed.
     * @return long id of request
     */
    long createRequest(CreateOrderController.CreateRequestForm createRequestForm);
}
