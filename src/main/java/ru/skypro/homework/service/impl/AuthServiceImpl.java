package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.generatedDto.NewPasswordDto;
import ru.skypro.homework.dto.generatedDto.RegisterDto;
//import ru.skypro.homework.entity.Authorities;
import ru.skypro.homework.entity.Authorities;
//import ru.skypro.homework.entity.MyUser;
import ru.skypro.homework.entity.User;
//import ru.skypro.homework.repository.AuthoritiesRepository;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.repository.AuthoritiesRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
//    private final MyUser myUser;

    public AuthServiceImpl(UserDetailsManager manager, PasswordEncoder encoder, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.manager = manager;
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

//    public AuthServiceImpl(UserDetailsManager manager,
//                           PasswordEncoder passwordEncoder, UserRepository userRepository, AuthoritiesRepository authoritiesRepository, MyUser myUser) {
//        this.manager = manager;
//        this.encoder = passwordEncoder;
//        this.userRepository = userRepository;
//        this.authoritiesRepository = authoritiesRepository;
//        this.myUser = myUser;
//    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            System.out.println("No");
        } else {
            System.out.println("yes");
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
//        myUser.setUser(userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new));
        return encoder.matches(password, userDetails.getPassword());
    }

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

        Authorities authorities = new Authorities();
        authorities.setUsername(registerDto.getUsername());
        authorities.setAuthority(registerDto.getRole());
        authoritiesRepository.save(authorities);
        return true;
    }

//    @Override
//    public void changePassword(NewPasswordDto passwordDto, Authorities authorities) {
//        Optional<User> user = userRepository.findByUsername(authorities.getUsername());
//        if (user.isPresent()) {
//        String encryptedPassword = user.get().getPassword();
//        if (encoder.matches(passwordDto.getCurrentPassword(), encryptedPassword)) {
//            user.get().setPassword(encoder.encode(passwordDto.getNewPassword()));
//            userRepository.save(user.get());
//        } else {
//            throw new UserHasNoRightsException("User inputs wrong current password");
//        }}
//    }

    @Override
    public void changePassword(NewPasswordDto newPasswordDto) {
        manager.changePassword(newPasswordDto.getCurrentPassword(), encoder.encode(newPasswordDto.getNewPassword()));
    }

}
