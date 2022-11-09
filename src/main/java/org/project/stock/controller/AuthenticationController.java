package org.project.stock.controller;

import lombok.RequiredArgsConstructor;
import org.project.stock.cookie.CookieWithToken;
import org.project.stock.security.context.UserContext;
import org.project.stock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final UserService userService;
    private final CookieWithToken cookie;

    @PostMapping("/auth")
    public ResponseEntity<String> signIn(HttpServletResponse response,
                                         @Valid @RequestBody UserContext userContext) {
        cookie.createCookieWithToken(response, userService.signIn(userContext));
        return new ResponseEntity<>("Аутентификая прошла успешно!",
                HttpStatus.OK);
    }
}
