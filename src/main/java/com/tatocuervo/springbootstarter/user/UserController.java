package com.tatocuervo.springbootstarter.user;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.routes.Routes;
import com.tatocuervo.springbootstarter.user.dto.CreateUserRequest;
import com.tatocuervo.springbootstarter.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("Creates new user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(conversionService.convert(createUserRequest, AppUser.class));
    }

    @ApiOperation("Get all users")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer jwt_token")
    public List<AppUser> getUser() {
        return userService.getUsers();
    }
}
