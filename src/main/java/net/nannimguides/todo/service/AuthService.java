package net.nannimguides.todo.service;

import net.nannimguides.todo.dto.LoginDto;
import net.nannimguides.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
