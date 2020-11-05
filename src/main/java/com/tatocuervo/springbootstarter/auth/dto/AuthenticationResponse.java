package com.tatocuervo.springbootstarter.auth.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AuthenticationResponse {
    private String jwtToken;

    @JsonCreator
    public AuthenticationResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
