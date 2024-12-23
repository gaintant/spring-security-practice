package  com.example.security_practice.configuration;

import com.example.security_practice.configuration.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String cookieName = "auth";
        Optional<Cookie> cookieOptional = Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream) // Convert array to stream
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findFirst();
        if(cookieOptional.isPresent()){

            Cookie cookie = cookieOptional.get();
            jwtUtils.verifyToken(cookie.getValue());
            filterChain.doFilter(request, response);
        }
    }
}

