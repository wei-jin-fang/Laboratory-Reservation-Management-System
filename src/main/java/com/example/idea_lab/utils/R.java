package com.example.idea_lab.utils;

import lombok.Data;

@Data
public class R {
    private int status;
    private String message;
    private boolean success;
    private Object data;
}
