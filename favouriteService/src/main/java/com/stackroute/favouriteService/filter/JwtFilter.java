package com.stackroute.favouriteService.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)req;
        final HttpServletResponse response = (HttpServletResponse)resp;
        final String authHeader = request.getHeader("authorization");

        if("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, resp);
        }else {
            if(authHeader == null || !authHeader.startsWith("Bearer "))
                throw new ServletException("Missing or Invalid Authorization Header.");
        }

        final String token = authHeader.substring(7);
        final Claims claims = Jwts.parser()
                .setSigningKey("Userkey")
                .parseClaimsJws(token)
                .getBody();

        request.setAttribute("claims", claims);
        chain.doFilter(req, resp);
    }

}
