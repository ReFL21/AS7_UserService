package com.example.User_Service.filter;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.User_Service.security.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = {JwtAuthenticationFilter.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class JwtAuthenticationFilterDiffblueTest {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private UserDetailsService userDetailsService;

    /**
     * Test {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}.
     * <ul>
     *   <li>Given {@code https://example.org/example}.</li>
     * </ul>
     * <p>
     * Method under test: {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @DisplayName("Test doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain); given 'https://example.org/example'")
    @Tag("MaintainedByDiffblue")
    void testDoFilterInternal_givenHttpsExampleOrgExample() throws ServletException, IOException {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain chain = mock(FilterChain.class);
        doNothing().when(chain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, chain);

        // Assert
        verify(chain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Test {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}.
     * <ul>
     *   <li>Given {@link JwtUtil}.</li>
     *   <li>When {@link MockHttpServletRequest#MockHttpServletRequest()}.</li>
     *   <li>Then calls {@link FilterChain#doFilter(ServletRequest, ServletResponse)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @DisplayName("Test doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain); given JwtUtil; when MockHttpServletRequest(); then calls doFilter(ServletRequest, ServletResponse)")
    @Tag("MaintainedByDiffblue")
    void testDoFilterInternal_givenJwtUtil_whenMockHttpServletRequest_thenCallsDoFilter()
            throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain chain = mock(FilterChain.class);
        doNothing().when(chain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, chain);

        // Assert
        verify(chain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
    }

    /**
     * Test {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}.
     * <ul>
     *   <li>Then calls {@link JwtUtil#extractUsername(String)}.</li>
     * </ul>
     * <p>
     * Method under test: {@link JwtAuthenticationFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    @DisplayName("Test doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain); then calls extractUsername(String)")
    @Tag("MaintainedByDiffblue")
    void testDoFilterInternal_thenCallsExtractUsername() throws ServletException, IOException {
        // Arrange
        when(jwtUtil.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtUtil.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain chain = mock(FilterChain.class);
        doNothing().when(chain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, chain);

        // Assert
        verify(jwtUtil).extractUsername(eq(""));
        verify(jwtUtil).validateToken(eq(""), isNull());
        verify(chain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }
}
