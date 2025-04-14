package com.restapi_example1.excpetion;

import java.util.Date;

public class ErrorDetails {
    private String name;
    private Date date;
    private String description;

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public ErrorDetails(String name, Date date, String description) {
        this.name = name;
        this.date = date;
        this.description = description;
    }
}
