package ru.skypro.homework.service;

import ru.skypro.homework.dto.generatedDto.UserDto;

public interface UserService {
    UserDto getUserDtoByUsername(String username);
}
