package hu.bosch.bomple.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtResponseFilter extends GenericFilterBean {

    private final JwtFacade jwtFacade;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (jwtFacade.isLoggedIn()) {
            String jwt = jwtFacade.getOriginalJwt();
            // todo: ez akkor nagyon szép, ha CSAK FRISSEN MEGÚJULT jwt esetén fut le
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Authorization", jwt);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
