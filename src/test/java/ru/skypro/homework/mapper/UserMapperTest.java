package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.generatedDto.CommentDto;
import ru.skypro.homework.dto.generatedDto.UserDto;
import ru.skypro.homework.entity.Avatar;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.skypro.homework.dto.Role.ADMIN;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Test
    void shouldMapUserToUserDto() {
        //given
        Avatar avatar = new Avatar();
        avatar.setId(1L);
        User testUser = new User();
        testUser.setId(42L);
        testUser.setUsername("testUsername");
        testUser.setFirstName("testFirstName");
        testUser.setLastName("TestLastName");
        testUser.setRole(ADMIN);
        testUser.setPhone("+71234567890");
        testUser.setAvatar(avatar);

        //when
        UserDto userDto = userMapper.userToUserDto(testUser);

        //then
        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(testUser.getId().intValue());
        assertThat(userDto.getEmail()).isEqualTo(testUser.getUsername());
        assertThat(userDto.getFirstName()).isEqualTo(testUser.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(testUser.getLastName());
        assertThat(userDto.getRole()).isEqualTo(testUser.getRole());
        assertThat(userDto.getPhone()).isEqualTo(testUser.getPhone());
        assertThat(userDto.getImage()).isEqualTo("/users/avatar/" + avatar.getId());
    }
}