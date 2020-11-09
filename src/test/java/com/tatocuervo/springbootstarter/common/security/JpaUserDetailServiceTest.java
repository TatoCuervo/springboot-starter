package com.tatocuervo.springbootstarter.common.security;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class JpaUserDetailServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JpaUserDetailService tested;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldThrowExceptionIfUserDoesNotExists() {
        final String username = "username";

        // Given
        given(userRepository.findByUsername(username)).willReturn(null);

        // When
        assertThatThrownBy(() -> tested.loadUserByUsername(username)).isInstanceOf(UsernameNotFoundException.class);
    }

    @Test
    void shouldReturnUserFromUsername() {
        final String username = "username";
        final String password = "secure123";
        final String role = "ROLE";
        final AppUser appUser = AppUser.builder().username(username)
                .password(password)
                .role(role)
                .build();
        final User expected = new User(username, password,
                Collections.singletonList(new SimpleGrantedAuthority(role)));

        // Given
        given(userRepository.findByUsername(username)).willReturn(appUser);

        // When
        UserDetails actual = tested.loadUserByUsername(username);

        // Then
        assertThat(actual).isEqualTo(expected);
    }

}