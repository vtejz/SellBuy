package com.stackroute.productService.Filter;

import io.jsonwebtoken.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq=  (HttpServletRequest) request;
        HttpServletResponse httpresp=(HttpServletResponse) response;

//Adding CORS configuration
        httpresp.setHeader("Access-Control-Allow-Origin", "*");
        httpresp.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
        httpresp.setHeader("Access-Control-Allow-Credentials", "true");
        httpresp.setHeader("Access-Control-Allow-Headers", "*");

        //getting header infomation
        String authheader=httpreq.getHeader("Authorization");

        if((authheader==null) || (!authheader.startsWith("Bearer")))
        {
            throw new ServletException("JWT Token is missing");
        }


        String tok=authheader.substring(7);

        try
        {
            JwtParser jwtparserobj= Jwts.parser().setSigningKey("Userkey");
            Jwt jwtobj=jwtparserobj.parse(tok);
            //to reurn the payload information
            Claims claim=(Claims)jwtobj.getBody();
            System.out.println("user created token is " + claim.getSubject());
        }
        catch(SignatureException exc)
        {
            throw new ServletException("signature mismatch");
        }
        catch (MalformedJwtException exc)
        {
            throw new ServletException("token is modified by unauthorized user");
        }
        chain.doFilter(httpreq, httpresp);


    }
}
