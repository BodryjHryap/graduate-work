package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.RegisterDto;
import ru.skypro.homework.entity.Authorities;
import ru.skypro.homework.entity.User;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto);

//    void changePassword(NewPasswordDto newPasswordDto, Authorities authorities);
    void changePassword(NewPasswordDto newPasswordDto);
}
