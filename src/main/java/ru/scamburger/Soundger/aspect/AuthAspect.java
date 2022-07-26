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

    private final AuthService authService;

    public AuthAspect(AuthService authService) {
        this.authService = authService;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }



    @Before("@annotation(ru.scamburger.Soundger.annotation.Authorized)")
    public void before(){
        boolean authorize;
        try {
             authorize=authService.isAuthorized(getRequest().getHeader("Authorization"));
        }
        catch (NoResultException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Token not found");
        }
            if (authorize) {
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"User unauthorized");
            }
    }
}
