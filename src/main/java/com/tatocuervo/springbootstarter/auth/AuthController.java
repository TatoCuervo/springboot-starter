package com.tatocuervo.springbootstarter.auth;

import com.tatocuervo.springbootstarter.auth.dto.AuthenticationRequest;
import com.tatocuervo.springbootstarter.auth.dto.AuthenticationResponse;
import com.tatocuervo.springbootstarter.common.exception.InvalidUserCredentialsException;
import com.tatocuervo.springbootstarter.common.jwt.JwtUtil;
import com.tatocuervo.springbootstarter.routes.Routes;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Authentication")
@RestController
@RequestMapping(path = Routes.AUTHENTICATE)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        // verify credentials are valid, throw exception otherwise
        authenticate(authenticationRequest);

        // retrieve user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // generate jwt token
        String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }

    private void authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidUserCredentialsException();
        }
    }
}
