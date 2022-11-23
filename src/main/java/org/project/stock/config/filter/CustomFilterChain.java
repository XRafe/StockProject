package org.project.stock.config.filter;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.project.stock.cookie.CookieWithToken;
import org.project.stock.security.JwtAction;
import org.project.stock.security.impl.AuthenticationImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomFilterChain extends GenericFilterBean {

    private final JwtAction jwtAction;
    private final CookieWithToken cookie;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String token = cookie.getCookieWithToken(servletRequest);
        setAuthenticationUser(token);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setAuthenticationUser(String token) {
        if (token != null && jwtAction.validateToken(token)) {
            Claims claims = jwtAction.getClaims(token);

            AuthenticationImpl authentication = new AuthenticationImpl();
            authentication.setLogin(claims.getSubject());
            authentication.setAuthenticated(true);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}
