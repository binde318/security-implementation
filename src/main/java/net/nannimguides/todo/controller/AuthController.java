package net.nannimguides.todo.controller;

import lombok.AllArgsConstructor;
import net.nannimguides.todo.dto.LoginDto;
import net.nannimguides.todo.dto.RegisterDto;
import net.nannimguides.todo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;
    //build register API
@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
     String response= authService.register(registerDto);
     return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
//build login API
@PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody LoginDto loginDto){
    String response=authService.login(loginDto);
    return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
