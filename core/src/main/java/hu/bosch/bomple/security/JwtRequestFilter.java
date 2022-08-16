package hu.bosch.bomple.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JwtRequestFilter extends GenericFilterBean {

    private final AuthenticationProvider authProvider;

    private final Set<RequestMatcher> secured;
    private final Set<RequestMatcher> unprotected;

    protected JwtRequestFilter(AuthenticationProvider authProvider, String[] secured, String[] unprotected) {
        this.authProvider = authProvider;

        this.secured = new HashSet<>();
        Arrays.asList(secured).forEach(url -> this.secured.add(new AntPathRequestMatcher(url)));
        this.unprotected = new HashSet<>();
        Arrays.asList(unprotected).forEach(url -> this.unprotected.add(new AntPathRequestMatcher(url)));
    }

    /**
     * GenericFilterBean-ből következő kötelezően implementálandó metódus
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    /**
     * A doFilter "magyarul", a megfelelő típusokra castolva
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!requiresAuthentication(request)) {
            chain.doFilter(request, response);
            return; // védtelen patheken semmi dolgunk
        }
        try {
            Authentication authenticationResult = attemptAuthentication(request, response);
            successfulAuthentication(request, response, chain, authenticationResult);
        }
        catch (AuthenticationException ex) {
            log.warn(String.format("%s during authentication!", ex.getClass().getSimpleName()));
            unsuccessfulAuthentication(request, response, ex);
        }
    }


    private Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String jwt = request.getHeader("Authorization");
        if (!StringUtils.hasText(jwt)) {
            throw new BadCredentialsException("házi feladat");
        }
        jwt = jwt.substring(7);
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);

        return authProvider.authenticate(authentication);
    }

    /**
     * Sikeres authentikáció esetén
     */
    private void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    /**
     * Sikertelen authentikáció esetén
     */
    private void unsuccessfulAuthentication(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        // TODO: kezelni
    }

    /**
     * Eldönti, hogy az adott /path -en szükséges-e authentikálni
     */
    private boolean requiresAuthentication(HttpServletRequest request) {
        boolean requiresAuth = isBlacklisted(request) && !isWhiteListed(request);
        // értelmezés:
        // ha nem blacklisted, akkor biztos nem kell auth
        // ha blacklisted, akkor a whitelisten szereplés "megmentheti"
        // azért, mert a blacklistben számítunk a /** -ra, ebben kellhet kivételt képezni
        // pl: a /login természetesen a /**-nak is megfelel, de ott NEM akarunk authot
        log.debug(
                String.format(
                        "Received request on: %s - auth required: %s",
                        request.getRequestURL().toString(), String.valueOf(requiresAuth)));
        return requiresAuth;
    }

    private boolean isBlacklisted(HttpServletRequest request) {
        if (this.secured.isEmpty()) {
            return false;
        }
        return this.secured.stream().anyMatch(url -> url.matches(request));
    }

    private boolean isWhiteListed(HttpServletRequest request) {
        if (this.unprotected.isEmpty()) {
            return false;
        }
        return this.unprotected.stream().anyMatch(url -> url.matches(request));
    }

}
