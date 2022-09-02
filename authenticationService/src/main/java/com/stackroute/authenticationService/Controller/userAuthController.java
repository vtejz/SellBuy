package com.stackroute.authenticationService.Controller;


import com.stackroute.authenticationService.Exception.UserNotFoundException;
import com.stackroute.authenticationService.Exception.UserNullException;
import com.stackroute.authenticationService.Model.User;
import com.stackroute.authenticationService.Service.userAuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class userAuthController {

    @Autowired
    userAuthService userAuthService1;

    private final Logger logger = LoggerFactory.getLogger(userAuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String jwtToken = "";

        Map<String, String> map = new HashMap<>();
        try {
            jwtToken = getToken(user.getUserId(), user.getPassword());
            map.put("token", jwtToken);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

        }
        logger.info("In controller - {}", "Authorized User.");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    public String getToken(String userId, String password) throws UserNullException, UserNotFoundException {
        if (userId == null || password == null) {
            throw new UserNullException("Please fill in username and password.");
        }

        User isUserExists = userAuthService1.findByUserIdAndPassword(userId, password);

        if (isUserExists == null) {
            throw new UserNotFoundException("Invalid Credentials");
        }

        String jwtToken = Jwts.builder().setSubject(userId).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, "Userkey").compact();

        logger.info("JWT Token created Successfully");
        return jwtToken;

    }

}
