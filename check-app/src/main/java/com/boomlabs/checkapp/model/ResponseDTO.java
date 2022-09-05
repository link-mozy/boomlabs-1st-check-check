package com.boomlabs.checkapp.model;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 8371380860370944955L;

    private String status;
    private String code;
    private Object data;
    private String message;
}
