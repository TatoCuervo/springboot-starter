package com.tatocuervo.springbootstarter.common.security.filter;

import com.tatocuervo.springbootstarter.common.jwt.JwtUtil;
import com.tatocuervo.springbootstarter.common.security.JpaUserDetailService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Verifies every authenticated API request holds a valid JWT token
 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JpaUserDetailService jpaUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith(JwtUtil.JWT_TOKEN_PREFIX)) {
            String jwt = requestTokenHeader.substring(JwtUtil.JWT_TOKEN_PREFIX.length());

            try {
                // verify token is valid
                if (jwtUtil.validateToken(jwt)) {
                    // load user details from DB
                    UserDetails userDetails = jpaUserDetailService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwt));

                    // create spring authentication
                    Authentication authentication = createAuthentication(userDetails, request);

                    // set authenticated user to spring context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JwtException | IllegalArgumentException e) {
                SecurityContextHolder.clearContext();
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Provided JWT token is invalid or expired");
                return;
            }
        }
        // resume filters chain execution
        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return usernamePasswordAuthenticationToken;
    }
}
