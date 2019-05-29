package com.ian.gamerestapi.exception;

import java.io.IOException;

public class IdNotFoundException extends IOException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
