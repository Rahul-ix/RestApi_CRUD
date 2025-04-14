package com.restapi_example1.excpetion;

public class ResourceNotFound extends Exception {

    public ResourceNotFound(String message) {
        super(message);
    }
}
