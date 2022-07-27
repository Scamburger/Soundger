package ru.scamburger.Soundger.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;
import ru.scamburger.Soundger.service.AuthService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Configuration
public class AuthAspect {

    private static final String HEADER_NAME = "Soundger-Authorization";
    private final AuthService authService;

    public AuthAspect(AuthService authService) {
        this.authService = authService;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }

    @Before("@annotation(ru.scamburger.Soundger.annotation.Authorized)")
    public void before() {
        // Trying to read header value from http request
        String token = getRequest().getHeader(HEADER_NAME);

        // If there is no header in request or value is empty we should return error
        if (token == null || token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Method requires header 'Authorization'");
        }

        try {
            boolean authorize = authService.isAuthorized(token);
            if (!authorize) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } catch (NoResultException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal server error: " + e.getMessage());
        }
    }

}