package ua.edu.ukma.e_request.services.implementations;

import ua.edu.ukma.e_request.entities.StatusChanges;
import ua.edu.ukma.e_request.repositories.StatusChangesDAO;
import ua.edu.ukma.e_request.services.interfaces.StatusService;

public class StatusChangeImpl implements StatusService {

    private final StatusChangesDAO statusChangesDAO;

    public StatusChangeImpl(StatusChangesDAO statusChangesDAO) {
        this.statusChangesDAO = statusChangesDAO;
    }

    @Override
    public void createReport(StatusChanges report) {
        statusChangesDAO.save(report);
    }
}
