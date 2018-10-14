package ua.edu.ukma.e_request.services.interfaces;

import ua.edu.ukma.e_request.controller.CreateOrderController;
import ua.edu.ukma.e_request.entities.StatusChanges;

public interface StatusService {

    void createReport(StatusChanges report);
}
