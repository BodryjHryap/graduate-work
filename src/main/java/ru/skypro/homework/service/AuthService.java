package ru.skypro.homework.service;

import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto);

    void changePassword(NewPasswordDto newPasswordDto);
}
