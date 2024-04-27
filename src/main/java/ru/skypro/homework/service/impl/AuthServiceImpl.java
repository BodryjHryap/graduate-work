package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
    }
    /**
     * Авторизация пользователя
     *
     * @param userName Имя/почта пользователя
     * @param password пароль пользователя
     * @return возвращает boolean результат авторизации
     */
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }
    /**
     * Регистрация нового пользователя
     *
     * @param registerDto новые учетные данные пользователя от клиента
     * @return возвращает boolean результат регистрации
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        Optional<User> userByUsername = userRepository.findByUsername(registerDto.getUsername());
        if (userByUsername.isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(encoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhone(registerDto.getPhone());
        user.setRole(registerDto.getRole());
        user.setEnabled(true);

        userRepository.save(user);
        return true;
    }
    /**
     * Метод, который обновляет пароль от кабинета пользователя.
     * <br> Используются метод класса {@link UserDetailsManager#changePassword(String, String)}
     * @param newPasswordDto новый пароль
     */
    @Override
    public void changePassword(NewPasswordDto newPasswordDto) {
        manager.changePassword(newPasswordDto.getCurrentPassword(), encoder.encode(newPasswordDto.getNewPassword()));
    }
}
