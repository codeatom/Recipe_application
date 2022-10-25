package recipe_application.application.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Map;


public class APIError {

    private Integer statusCode;

    private String statusValue;

    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    private Map<String, String> errors;


    public APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(Integer statusCode, String statusValue) {
        this();
        this.statusCode = statusCode;
        this.statusValue = statusValue;
    }

    public APIError(Integer statusCode, String statusText, String message) {
        this(statusCode, statusText);
        this.message = message;
    }

    public APIError(Integer statusCode, String statusValue, String message, Map<String, String> errors) {
        this(statusCode, statusValue, message);
        this.errors = errors;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
