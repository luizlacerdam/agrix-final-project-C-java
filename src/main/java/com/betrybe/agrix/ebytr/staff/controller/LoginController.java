package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.LoginDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.LoginResDto;
import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login Controller.
 */
@RestController
@RequestMapping("/auth")
public class LoginController {

  private final AuthenticationManager authenticationManager;

  private final TokenService tokenService;

  /**
   * Constructor login controller..
   */
  @Autowired
  public LoginController(AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Login route com auth.
   */
  @PostMapping("/login")
  public ResponseEntity<LoginResDto> login(@RequestBody LoginDto loginDto) {

    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());

    authenticationManager.authenticate(usernamePassword);


    String token = tokenService.generateToken(loginDto.username());
    System.out.println(token);
    return ResponseEntity.status(HttpStatus.OK).body(new LoginResDto(token));
  }

}