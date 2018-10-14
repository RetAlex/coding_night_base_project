package ua.edu.ukma.e_request.resources.enums;

import java.io.Serializable;

/**
 * Created by nasti on 10/13/2018.
 */
public enum RequestStatus implements Serializable {
    NEW, PENDING_FOR_SUBMITION, APPROVED_BY_MENTOR, APPROVED_BY_DEAN, APPROVED_BY_THIRD, ROOM_APPROVED, APPROVED_BY_ECONOM_PRES;

    public RequestStatus getNextStatus() {
        return RequestStatus.values()[this.ordinal() + 1];
    }

    public RequestStatus getPrevious() {
        return RequestStatus.values()[this.ordinal() - 1];
    }
}
