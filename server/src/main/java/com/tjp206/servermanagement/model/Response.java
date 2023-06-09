package com.tjp206.servermanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class Response {

    protected LocalDateTime timeStamp;
    protected int serverStatusCode;
    protected HttpStatus status;
    protected String reason;
    protected String successMessage;
    protected String developerMessage;
    /*
    * Note to self:
    *  '?' is a wildcard that can be skipped and still get same result.
    * Can use any/every object for key and value in the map.
    */
    protected Map<?,?> data;
}
