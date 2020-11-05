package com.tatocuervo.springbootstarter.user;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.routes.Routes;
import com.tatocuervo.springbootstarter.user.dto.CreateUserRequest;
import com.tatocuervo.springbootstarter.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Users")
@RestController
@RequestMapping(path = Routes.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ConversionService conversionService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(conversionService.convert(createUserRequest, AppUser.class));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppUser> getUser() {
        return userService.getUsers();
    }
}
