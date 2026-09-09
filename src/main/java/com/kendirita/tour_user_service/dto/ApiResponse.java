package com.kendirita.tour_user_service.dto;

//import lombok.Data;
//
//@Data
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
}