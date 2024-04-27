package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.generatedDto.UserDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.EmptyFileException;
import ru.skypro.homework.exception.UserHasNoRightsException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.exception.ImageNotFoundException;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
        this.userMapper = userMapper;
    }
    /**
     * Получение пользователя из БД по имени/почте
     */
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    /**
     * Получение пользователя из БД по имени/почте и преобразование в объект класса UserDto
     */
    @Override
    public UserDto getUserDtoByUsername(String username) {
        User user = getUser(username);
        return userMapper.userToUserDto(user);
    }

    /**
     * Метод, который возвращает авторизованного пользователя.
     * <br><br> Используется объект SecurityContextHolder.
     * <br> В нем мы храним информацию о текущем контексте безопасности приложения, который включает в себя информацию о пользователе работающем в настоящее время с приложением.
     * @return возвращает объект пользователя UserDto
     */
    @Override
    public UserDto getAuth() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principalUser = (UserDetails) currentUser.getPrincipal();
        return userMapper.userToUserDto(userRepository.findByUsername(principalUser.getUsername()).get());
    }
    /**
     * Изменение пользователя
     */
    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto currentUser = this.getAuth();
        User updatedUser = userRepository.findByUsername(currentUser.getEmail()).get();
        updatedUser.setFirstName(userDto.getFirstName());
        updatedUser.setLastName(userDto.getLastName());
        updatedUser.setPhone(userDto.getPhone());
        userRepository.save(updatedUser);
        return userMapper.userToUserDto(updatedUser);
    }

    /**
     * Изменение аватара пользователя
     */
    @Override
    public void updateUserAvatar(String username, MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }

        User currentUser = getUser(username);

        Avatar avatar = avatarRepository.findByUserId(currentUser.getId()).orElse(new Avatar());

        avatar.setUser(currentUser);

        try {
            avatar.setImage(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Problems with uploaded image");
        }

        avatarRepository.save(avatar);
    }
    /**
     * Получение аватара пользователя
     */
    @Override
    public byte[] getUserAvatar(long id) {
        Avatar avatar = avatarRepository.findById(id).orElseThrow(ImageNotFoundException::new);
        return avatar.getImage();
    }

    /**
     * Проверка прав пользователя
     */
    @Override
    public void checkUserHasPermit(Authentication authentication, String username) {
        boolean matchUser = authentication.getName().equals(username);
        boolean userIsAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().contains(Role.ADMIN.name()));

        if (!(userIsAdmin || matchUser)){
            throw new UserHasNoRightsException("Current user has no rights to perform this operation.");
        }
    }
}
