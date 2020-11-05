package com.tatocuervo.springbootstarter.user.converters;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.user.dto.CreateUserRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserFromCreateUserRequest implements Converter<CreateUserRequest, AppUser> {

    @Override
    public AppUser convert(CreateUserRequest createUserRequest) {
        return AppUser.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .role(createUserRequest.getRole())
                .build();
    }
}
