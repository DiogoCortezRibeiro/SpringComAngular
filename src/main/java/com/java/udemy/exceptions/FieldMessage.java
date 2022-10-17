package com.java.udemy.exceptions;

import lombok.Data;

@Data
public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage() {
        super();
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
