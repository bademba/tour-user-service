package com.kendirita.tour_user_service.dto;

public class ApiResponse<T> {

    private T data;
    private String message;
    private String responseId;
    private int status;
    private String timestamp;


    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getResponseId() {
        return responseId;
    }

    public int getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}