package ua.edu.ukma.e_request.resources.enums;

import java.io.Serializable;

/**
 * Created by a.bondarenko on 10/13/2018.
 */
public enum Role implements Serializable {
    STUDENT, MENTOR(RequestStatus.NEW), DEKAN(RequestStatus.APPROVED_BY_MENTOR), FOND, VICE_PRESIDENT(RequestStatus.ROOM_APPROVED);

    private RequestStatus appliedRequests;

    Role(RequestStatus status) {
        this.appliedRequests = status;
    }

    Role() {
    }
}
