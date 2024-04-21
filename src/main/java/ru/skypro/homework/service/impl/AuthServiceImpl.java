package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.generatedDto.RegisterDto;
import ru.skypro.homework.entity.Authorities;
import ru.skypro.homework.entity.User;
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

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            System.out.println("No");
        } else {
            System.out.println("yes");
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        System.out.println(userDetails);
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

        userRepository.save(user);

        Authorities authorities = new Authorities();
        authorities.setUsername(registerDto.getUsername());
        authorities.setAuthority(registerDto.getRole());

        authoritiesRepository.save(authorities);

        return true;
    }

}
