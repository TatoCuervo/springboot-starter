package com.tatocuervo.springbootstarter.user;

import com.tatocuervo.springbootstarter.common.model.User;
import com.tatocuervo.springbootstarter.routes.Routes;
import com.tatocuervo.springbootstarter.user.dto.CreateUserRequest;
import com.tatocuervo.springbootstarter.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    public void addUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(conversionService.convert(createUserRequest, User.class));
    }

    @GetMapping
    public List<User> getUser() {
        return userService.getUsers();
    }
}
