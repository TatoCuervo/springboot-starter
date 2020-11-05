package com.tatocuervo.springbootstarter.user.converters;

import com.tatocuervo.springbootstarter.common.model.User;
import com.tatocuervo.springbootstarter.user.dto.CreateUserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserFromCreateUserRequest implements Converter<CreateUserRequest, User> {

    @Override
    public User convert(CreateUserRequest createUserRequest) {
        return User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .role(createUserRequest.getRole())
                .build();
    }
}
