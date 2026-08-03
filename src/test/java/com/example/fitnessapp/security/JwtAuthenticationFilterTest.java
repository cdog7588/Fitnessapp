package com.example.fitnessapp.security;

import com.example.fitnessapp.security.AppUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

class JwtAuthenticationFilterTest {

    @Test
    void returnsUnauthorizedForMalformedBearerToken() throws Exception {
        JwtService jwtService = new JwtService("a-test-secret-that-is-longer-than-thirty-two-characters");
        AppUserDetailsService userDetailsService = mock(AppUserDetailsService.class);
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtService, userDetailsService);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer malformed.token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(request, response, mock(jakarta.servlet.FilterChain.class));

        assertEquals(401, response.getStatus());
        assertEquals("application/json", response.getContentType());
        verifyNoInteractions(userDetailsService);
    }
}
