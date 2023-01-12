package com.javalab.java_lab.model;

import lombok.Data;

@Data
public class ErrorMessage {
    

    private String errorCode;
    private String errorMessage;
    private String subCode;
    private String details;

   
}
