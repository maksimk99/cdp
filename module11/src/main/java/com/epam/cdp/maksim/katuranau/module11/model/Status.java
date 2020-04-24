package com.epam.cdp.maksim.katuranau.module11.model;

import javax.validation.constraints.Min;

public class Status {
    @Min(value = 0, message = "identifier should not be negative")
    private Long statusId;
    private String status;

    public Status(final Long statusId, final String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(final Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }
}
