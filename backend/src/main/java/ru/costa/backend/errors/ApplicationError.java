package ru.costa.backend.errors;

import lombok.Data;

import java.util.Date;

/**
 * @author Constantin Vashchuk
 */

@Data
public class ApplicationError {
    private int httpStatus;
    private String description;
    private Date timestamp;

    public ApplicationError(int httpStatus, String description) {
        this.httpStatus = httpStatus;
        this.description = description;
        this.timestamp = new Date();
    }
}
